# Kotlin-TUI
Make a Kotlin Terminal User Interface from scratch 

I learned that inorder to run this I need to execute
```cmd
./gradlew clean fatJar; java -jar build/libs/Kotlin-TUI.jar
```

## What I have
I am able to print characters (all found under the symbols package) 
on the terminal with special ANSI codes (found in the ANSI object)

## TODO
Fully implement a digital representation of the screen as a char array so both limit regenerating text and to allow 
for two thing to change at the same time

## Resources
I used a bunch of public repos and resources to make this.  
Here is an ongoing list:
Unicode Character list: https://github.com/ehmicky/cross-platform-terminal-characters  

