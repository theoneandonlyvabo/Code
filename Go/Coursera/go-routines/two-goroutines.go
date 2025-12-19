package main

import (
	"fmt"
	"time"
)

var counter int

func increment() {
	for i := 0; i < 1000; i++ {
		counter++
	}
}

func decrement() {
	for i := 0; i < 1000; i++ {
		counter--
	}
}

func main() {
	counter = 0

	go increment()
	go decrement()

	time.Sleep(1 * time.Second)

	fmt.Println("Final counter value:", counter)
	fmt.Println("Expected value: 0")
}
