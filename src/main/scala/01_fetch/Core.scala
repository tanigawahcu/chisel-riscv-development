package fetch

import chisel3._
import chisel3.util._
import common.Consts._

class Core extends Module {
    val io = IO(new Bundle {
        // 命令メモリへの入出力を定義
        val imem = Flipped(new ImemPortIo())

        // 出力ポートexitを定義
        val exit = Output(Bool())
    })

    // レジスタファイルを定義
    val regfile = Mem(32, UInt(WORD_LEN.W))

    //******************************************
    // 命令フェッチステージ
    //******************************************
    // PCをSTART_ADDRで初期化して作成
    val pc_reg = RegInit(START_ADDR)

    pc_reg := pc_reg + 4.U(WORD_LEN.W)

    //出力ポートaddrにpc_regを接続詞，入力ポートinstをinstで受ける
    io.imem.addr := pc_reg
    val inst = io.imem.inst

    // exit信号はinstが16進数で"34333231"となった時にtrueとする
    io.exit := (inst === 0x34333231.U(WORD_LEN.W))

    // デバッグ用にprintf出力
    printf(p"pc_reg : 0x ${Hexadecimal(pc_reg)}\n")
    printf(p"inst : 0x${Hexadecimal(inst)}\n");
    printf("----------------\n");

}