# callme

A command line application that converts phone numbers into its equivalent word replacement from a dictionary. It reads from files specified as
command-line arguments or STDIN when no files are given. Each line of these files will contain a single phone number.  
By default it will look for dictionary.txt in the current directory.
The -d command-line option can override the dictionary name and location.

## Requirements
Build requires Maven with Java 8.  
Running requires Java 8.

## Limitations
The choice was to remove words containing numbers from dictionary words, or delete the offending characters. I choose the latter.

Letters found in phone numbers will be removed.  
Numbers found in words of the dictionary will be removed.

## Building application
It uses Apache maven as a build system, hence maven build commands, using java 8, should be used

## Run the application

    java -jar callme-0.0.1-SNAPSHOT.jar [file option] ... [-d dictionary option]
    
    file option: name of the file(s) containing the phone numbers
    dictionary option: name of the dictionary file containing words
    
    if no file option is used, it will read from stdin. 
    Enter "quit" (not case sensitive) to indicate the end of the manual phone number entry

When reading from stdin, "QUIT" is used to indicate end of entry. This was done because the use of EOF differs between operating systems and produced strange results in cygwin. 

If NO dictionary is specified, using the -d dictionary option, the program will look for a dictionary file called dictionary.txt in the local directory.

## Choice of challenge

 I only had the choice between 1-800-CODING-CHALLENGE and the Vehicle Survey Coding Challenge. The former seemed easy to understand and  more interesting. I had fun solving it and got a bit side tracked with different ways to implement it.

