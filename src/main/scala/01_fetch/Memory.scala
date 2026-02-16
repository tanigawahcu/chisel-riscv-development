package fetch

import chisel3._
import chisel3.util._
import chisel3.util.experimental._
import common.Consts._

/* 命令メモリへのIOインターフェースを定義 */
class ImemPortIo extends Bundle {
    val addr = Input(UInt(WORD_LEN.W))
    val inst = Output(UInt(WORD_LEN.W))
}

class Memory extends Module {
    val io = IO(new Bundle {
        val imem = new ImemPortIo()
    })

    // メモリの実態は8bit(1Byte)単位でアクセスできるメモリとする
    val mem = Mem(16384, UInt(8.W))

    // メモリデータをロード（hexファイルからロードしてくる）
    // シミュレーション用にはloadMemoryFromFileInlineを使い，
    // Verilog-HDL生成用にはloadMemoryFromFileを使うのが良いそうだ
    // ここでは，Consts.scala内のSIM変数のtrue/falseで使い分ける
    if( SIM ) {
        loadMemoryFromFileInline(mem, "src/hex/fetch.hex")
    } else {
        loadMemoryFromFile(mem, "src/hex/fetch.hex")
    }

    // 書くアドレスに格納された8bitデータを4つつなげて32bitデータとする
    io.imem.inst := Cat( 
        mem(io.imem.addr + 3.U(WORD_LEN.W)),
        mem(io.imem.addr + 2.U(WORD_LEN.W)),
        mem(io.imem.addr + 1.U(WORD_LEN.W)),
        mem(io.imem.addr)
    )

    // デバッグ用にprintf出力
    printf("mem(0) = %x\n", mem(0.U))
    printf("-------\n")
}