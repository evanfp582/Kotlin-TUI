# Kotlin-TUI

---
Make a Kotlin Terminal User Interface from scratch 

## TODO

---------------
Activities with higher priority are put to the top of the list.  
I am not sure what I want to do with done tasks tbh. Maybe just leave them 

- [ ] Better onEnter function for select
  - Like the ability to go to multiple screens
- [ ] Style in middle of text
  - I think I may have accidentally done this
- [ ] **IN PROG: Normal Buttons**
  - I think it is weird I did not think of this earlier
  - I need to design a button and how to show when a button is pressed
- [X] Multiline text/components
    - Partially implemented
- [X] Multiple screens
  - Very importantly I need to create a router type system
- [X] Component area ownership
    - [ ] Clearing owned areas
- [ ] Ownership of Curser 
  - Figure out ability to move cursor between components that can both use it
  - Tabbing between components feels like a pretty natural fix, but I do not know if I can shift tab, I do not know
- [ ] ASCI ART. Boxes, borders, lines, cool text
- Nested Components
- [ ] Markdown parser 
  - so I can just turn my/any readme into a page in my app
- [ ] Bouncing ball simulation
  - I don't have a good useful case where this would be important, but I just think dealing with the math,
  angles, and tick rate would be an interesting challenge to solve


I learned that in order to run this I need to execute
```cmd
./gradlew clean fatJar; java -jar build/libs/Kotlin-TUI.jar
```

I also made a "playground" main where I just mess with things. It is a very similar command  
```cmd
./gradlew clean playgroundJar; java -jar build/libs/Kotlin-TUI-Playground.jar
```

### Debugger
I am actually a litte proud on how quickly I was able to whip up a debug log because debugging in intelij for a TUI was a pain in the butt.  
Whenever you want to keep track of a log just write `DebugLogger.log("MESSAGE)` and it will appear in debug.log with a timestamp.
In order to keep track of the updating debug log you can run this command
`Get-Content .\debug.log -Wait` while in the Kotlin-TUI directory 

## What I have
I am able to print characters (all found under the symbols package) 
on the terminal with special ANSI codes (found in the ANSI object)

### Objects:
- ScreenObject
- UIManager
- Terminal
- DebugLogger
- Components

### Component List:
- Text- Simple text component
- Title- Multiline text component, little redundant 
- Select- Classic multi select component where something happens when you select an option
- TextBox- Input window for text
- Button- Press button, do something

## Dev logs

--------------------------------------------------------------
### Restructuring (9/1 or something)    
Right now the purpose of UIManager and ScreenObject kind of blend together

**Screen**  
This should be a group of components that get rendered  
Either rendered by default or the buildCondition is met and stays around until the destroyCondition is met or another screen's buildCondition is met  
Most of the functionality will actually stay the same, but some features from UIManager will be merged in   
Component array and collision check

**UIManager**  
This should run main loop  
Somewhat handle input, especially escapes  
Hold screen state and constantly check for a screen loading/destroying condition  
Screen stack to determine what to generate next

### A React favored approach to front end development (9/6)
I hate Python's Tkinter.  
It is a mess of folders, state, screens, and code jumble.   
Maybe I am bad at managing large python projects, I can concede this, but  
I never felt this way managing a React front end project.  
The Javascript and HTML work together on an element creating a logical chunk of code that is structured.  
On this project I am using kotlin's `apply` function to create very React looking code and I want it to function in a similar way.  
The main page should serve as the app component and basically be a router between the different separated nested component (screens).
Right now I am trying to implement a good routing system where the UIManager is basically always looking to see if a screen should be built or destroyed.  

**Ultimately What do I Want to Make**  
Probably nothing novel, this is more of learning experience/ introspection on my coding experience.  
I'd very much believe that there exists 10+ Kotlin/Java/Python TUI that do all that I want to do, but better.  
I want to create a React inspired TUI build in Kotlin that seamlessly merges front end generation with programmatic logic.  
But my real answer is I want to have fun, learn something new, and dig my teeth into a longer project.

### The flag approach to screen control (9/13)
The UIManager creates an array of booleans and essentially hand out a flag to the screen when it gets added  
The screen just needs to raise their flag (turn it true) and they will be given control of the screen  
I do not love the implementation I took. to both add custom OnEnter functions for select components and routing.  
The main.kt is supposed to represent a use making something of their own. I think that weird map is not very intuitive.  

### How to maneuver around a screen (9/28)
Since I am making more and more complex components that each require "control" of  the cursor I need to start to determine how 
to intuitively allow the user to switch between which components have control of the curser in the absense of a mouse.  
There are a few ways of doing this and I actually want to have fun with it
- Linked List!?
  - I can make a linked list object that just points to each component and if I press `tab` I go forward in the list and if I press `shift+tab` I go back in the list
  - To make a linked list for good instead of evil (LeetCode) actually sounds like a fun idea  
 - Tree!?
   - When components are created within a screen connect to other components so the user can use the arrow keys to traverse the screen
   - Certainly more complex, but also just a fun CS problem to solve
   - Works more intuitively with the Select component where I press up and down to select between options there  

 I am undecided and will need to make a choice as soon as I finish designing this button component


## Resources
I used a bunch of public repos and resources to make this.  
Here is an ongoing list:  
Unicode Character list- https://github.com/ehmicky/cross-platform-terminal-characters
A bunch of ASCI art websites. When I use a specific one I will make sure to credit the author, 
otherwise I will credit the site that I used to generate the art

