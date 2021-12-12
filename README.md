# RSA-Algorithm         
### Done By :
  • Omar Alqarni              -->       201751650
  • Abdulrahman Abdulltif     -->       201744290
        
  #### Table of Contents  
  ##### - [Overview](https://github.com/omarqr0/RSA-Algorithm#Overview)
  ##### - [Technical_overview](https://github.com/omarqr0/RSA-Algorithm#technical_overview)
  ##### - [Encryption](https://github.com/omarqr0/RSA-Algorithm#Encryption)
  ##### - [Decryption](https://github.com/omarqr0/RSA-Algorithm#Decryption)

        
## [Overview](https://github.com/omarqr0/RSA-Algorithm#Overview)
  The project's purpose is to encrypt and decrypt messages using the RSA Algoritm.
  The RSA algorithm is an asymmetric cryptography algorithm that has public and private keys. Also, it was founded by Ronald L. Rivest, Adi Shamir, and Leonard M.
  
  
## [Technical_overview](https://github.com/omarqr0/RSA-Algorithm#technical_overview)
  - The language used to implement this project was **Java**.
  - The encryption and decryption files are separated for safety.

## [Encryption](https://github.com/omarqr0/RSA-Algorithm#Encryption)
  - the alphabet are stored in a char array along with the digits and symbols
  - The system asks the user to enter the text file wich has:
    - n key
    - e key
    - text message   
  - the system translates each character of the message to its index number in the alphabet array, if the character's index is less than 10, __e.g.: B = 1__, the       system appends a zero to the left of the character, then adds it to a separate string.
  - Then, the system determines the n length, and the block size for each message, and splits the message based on the __block size__.
  - The system passes the splitted text as numbers, the e key, and n key to the modPow method where most of the encryption occurs.
  -  in the modPow method:
    - The array of text as numbers gets converted to a bigInteger class since it handles big mods and pows.
    - The method pows each block by the e key.
    - Then, it mods the powed block by the n key.
    - After that, the bigInteger Arraylist gets converted to long type. hence, the length of the result will no exceed 9,223,372,036,854,775,807.
    - lastly, the long arraylist gets parsed toString and returned to main.
  - Lastly, the system creates a file with the same input file but with the format of __.rsa__ . and writes the encrypted message using PrintWriter.

## [Decryption](https://github.com/omarqr0/RSA-Algorithm##Decryption)

- The alphabet are stored in a char array along with the digits and symbols
- the system asks the user to enter the d key and the n key
- the system asks the user to enter the file name without .rsa ending
- Then, the system stores the d key, the n key as long type, and the message.
- After that, it determines the n length and the block size. 
- Then, it splits the encrypted message by the block size.
- Now, it passes the n key, d key, the encrypted message, and the block size to decrypt method
- in the decrypt method:
    - The array of text as numbers gets converted to a bigInteger class since it handles big mods and pows.
    - The method pows each block by the d key.
    - Then, it mods the powed block by the n key.
    - After that, the bigInteger Arraylist gets converted to long type. hence, the length of the result will no exceed 9,223,372,036,854,775,807.
    - lastly, the long arraylist gets parsed to a string array and returned to main.
**Why a string array?**
- to keep the block size of each block unchanged
- once the decrypted message gets return to main, it enters a nested loop that does the following
    - reads each block
    - creates a temporary array that assigns each two indexes in the block
    - translate each couple of indexes as its opposite in the alphabet array
- lastly, the encrypted message gets writed in a file named the same as the input with the .dec extension using Print Writer
