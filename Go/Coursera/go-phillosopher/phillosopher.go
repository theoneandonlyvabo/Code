package main

import (
	"fmt"
	"sync"
)

type Chopstick struct {
	sync.Mutex
}

type Host struct {
	permission chan bool
}

func NewHost() *Host {
	h := &Host{
		permission: make(chan bool, 2),
	}
	h.permission <- true
	h.permission <- true
	return h
}

func (h *Host) requestPermission() {
	<-h.permission
}

func (h *Host) releasePermission() {
	h.permission <- true
}

func philosopher(id int, leftChopstick, rightChopstick *Chopstick, host *Host, wg *sync.WaitGroup) {
	defer wg.Done()

	for i := 0; i < 3; i++ {
		host.requestPermission()

		leftChopstick.Lock()
		rightChopstick.Lock()

		fmt.Printf("starting to eat %d\n", id)
		fmt.Printf("finishing eating %d\n", id)

		rightChopstick.Unlock()
		leftChopstick.Unlock()

		host.releasePermission()
	}
}

func main() {
	chopsticks := make([]*Chopstick, 5)
	for i := 0; i < 5; i++ {
		chopsticks[i] = &Chopstick{}
	}

	host := NewHost()

	var wg sync.WaitGroup
	wg.Add(5)

	for i := 0; i < 5; i++ {
		go philosopher(i+1, chopsticks[i], chopsticks[(i+1)%5], host, &wg)
	}

	wg.Wait()
}
