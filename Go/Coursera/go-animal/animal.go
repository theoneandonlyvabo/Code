package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

type Animal struct {
	food       string
	locomotion string
	noise      string
}

func (a Animal) Eat() {
	fmt.Println(a.food)
}

func (a Animal) Move() {
	fmt.Println(a.locomotion)
}

func (a Animal) Speak() {
	fmt.Println(a.noise)
}

func main() {
	animals := map[string]Animal{
		"cow":   {food: "grass", locomotion: "walk", noise: "moo"},
		"bird":  {food: "worms", locomotion: "fly", noise: "peep"},
		"snake": {food: "mice", locomotion: "slither", noise: "hsss"},
	}

	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("> ")
		scanner.Scan()
		input := scanner.Text()

		parts := strings.Fields(input)
		if len(parts) != 2 {
			fmt.Println("Invalid input. Please enter animal and action.")
			continue
		}

		animalName := strings.ToLower(parts[0])
		action := strings.ToLower(parts[1])

		animal, exists := animals[animalName]
		if !exists {
			fmt.Println("Unknown animal. Use cow, bird, or snake.")
			continue
		}

		switch action {
		case "eat":
			animal.Eat()
		case "move":
			animal.Move()
		case "speak":
			animal.Speak()
		default:
			fmt.Println("Unknown action. Use eat, move, or speak.")
		}
	}
}
