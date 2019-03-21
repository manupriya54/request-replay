# check if local g++ supports c++17 and if so, use it
std := $(shell echo "int main(){}" | g++ -std=c++17 -xc++ - 2>/dev/null && echo 17 || echo 11)
all:
	g++ -std=c++$(std) -ggdb3 -o replay replay.cpp $(wildcard cpr/*.cpp) -lcurl
