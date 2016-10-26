# callme

A command line application that converts phone numbers into its equivalend word replacement form a dictionary. It reads from files specified as
command-line arguments or STDIN when no files are given. Each line of these files will contain a single phone number.  
By default it will look for dictionary.txt in the current directory.
The -d command-line option can override the dictionary name and location.

## Requirements
Build requires Maven with Java 8.
Running requires Java 8.

## Limitiations
The choice was to remove invalid numbers from dictionary words or delete the offending characters. I choose the latter.

Letters found in phone numbers will be removed.
Numbers found in words of the dictionary will be removed.
