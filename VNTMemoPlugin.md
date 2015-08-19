# VNT Memo plugin #

Conver VNT memos into text files.

## Description ##
On my Samsung Galaxy S, the Android application **Memo** saves the notes as _.vnt_ files.

Once you download them on your computer, you want to view them just as it would show on your phone.
The Line Brakes in the note are preserved.

Since I haven't found any application that parses a .vnt note, I created this basic eclipse plugin to convert my .vnt memo files into text(.txt) files.

## Download the plug-in ##

Go to [source-> ... raz.memo.reader/exported](https://code.google.com/p/pxmanager/source/browse/#svn%2Fbranches%2FeclipseProjects%2Fraz.memo.reader%2Fexported) and right click on the .jar file, then `save link as/save target as` to save the .jar locally

## Install the plugin ##
  1. Shut down eclipse IDE
  1. Copy the .jar into `eclipse\dropins\` folder
  1. Start eclipse, the plugin should be installed.

[see eclipse dropins folder references](http://help.eclipse.org/juno/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fmisc%2Fp2_dropins_format.html)

## How to use the VNT Memo plugin ##
  1. Just create (or use an existing) an eclipse project
  1. Create a folder and copy your `.vnt` files into that folder
  1. Select one or more vnt files, right click, `MemoSubmenu->Memo Action`

## Thank you ##
If you download and use this just give me an email or leave me a comment, to encourage me. It would mean a lot to me to see that a person actually uses this, as it is my first endeavor of this kind.