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

I need to be able to embed style into the middle of the text associated with a component
For example I want the last char of a text box to be a blinking box, but honestly my line is not a bad solution

I need to not destroy a whole row when anything get edited on it. I need a way of determining ownership for each component  

- [ ] Style in middle of text
- [X] Multiline text/components
  - Partially implemented
- [ ] IN PROG: Multiple screens
- [X] Component area ownership
  - [ ] Clearing owned areas
- [ ] Ownership of Curser
  - [ ] Figure out ability to move cursor
- [ ] ASCI ART. Boxes, borders, lines, cool text
- Nested Components
- [ ] Bouncing ball simulation

### Restructuring    
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

## Resources
I used a bunch of public repos and resources to make this.  
Here is an ongoing list:  
Unicode Character list- https://github.com/ehmicky/cross-platform-terminal-characters  

