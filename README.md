

How to use:
 - This program uses MainGameLoop as the entry point.
 - There are no command line arguments
 - There is a line you can uncomment in MainGameLoop if you would like the board outputted in the console.
 
Debugging/Testing:
 - Only tool is a commented line in main of MainGameLoop used to print out...
 - ...the gameboard to the console to look for errors.
 - I believe I followed the provided design and will ask for clarification in lab or via grade.

Known bugs/unfinished features:

- There is no element comparison working so second element selected will be removed.
- When clicking, the second element selected will increment score even if not match.
- Since matching does not work, streak can only be broken by clicking on null spot (behind back coloured square).
- The jar created from following provided video does not work or is created wrong.
- Warnings about raw use of ArrayList and how the value used in comparing clicks may not contain correct object type.