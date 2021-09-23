package com.example.demo.exception

class NotEnoughStockException(message: String = "", cause: Throwable = Throwable()): RuntimeException(message, cause) {
//생성자 인자에 초기화를 하면 모든 생성자가 만들어진다.
}

