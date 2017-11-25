# CS141-FinalProject
A "simple" game made by people who don't know jack.

Change Log
----------
(from newest to oldest)

- fixed the bug where the user cannot get out of debug mode

- changed Save.WriteToFile to check if the directory "savefiles" does not exist instead of "user.dir"


List of Known Bugs
------------------
(remove these from the list once they are resolved)

format: [description] ; [way to reproduce the bug]

- when the user enters debug mode after looking in some direction, exiting debug mode will result in the 'looked at' square turning invisible instead of visible ; look in some direction, enter debug with "tdm404", exit debug with "tdm404"

- when the user looks in some direction and then tries to load a file that contains a state of the game where they are able to look in some direction, they are not given the option ; save a state of the game where you have the 'look' option, look in some direction, load the file that you just saved
