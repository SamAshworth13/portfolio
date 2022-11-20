.data    
#-----------------------------
#This is the data segment
#-----------------------------
strFirstDie: .asciiz "Die 1 result: "
strSecondDie: .asciiz "\nDie 2 result: "
strDragon: .asciiz "Dragon"
strOrc: .asciiz "Orc"
strSword: .asciiz "Sword"
strConnect: .asciiz " and "

.text 
#-------------------------------
#This is the body of the code
#-------------------------------

main:
#
#Initialise the registers by setting them to 0
#

li $t0, 0
li $t1, 0
li $t2, 0
li $t3, 0
li $t4, 0

generate:
#
#Generate a random number between 0 and 11
#

li $v0, 42
li $a1, 12
syscall

#
#Branch to firstVal if $t0 contains 0
#

beqz $t0, firstVal

secondVal:
#
#Store the random number in $t1 and add 1 then exit loop
#

add $t1, $a0, 1
b result

firstVal:
#
#Store the random number in $t0 and add 1 and branch to generate
#

add $t0, $a0, 1
b generate

result:
#
#Store the value from $t0 in $t2
#

move $t2, $t0

check:
#
#Check value of dice and relate to strings
#

ble $t2, 5, dragon
ble $t2, 8, sword
ble $t2, 12, orc
b display

dragon:
#
#Check if $t3 has been used, branch to dragFirstVal if not
#

beqz $t3, dragFirstVal

dragSecondVal:
#
#Store strDragon in $t4 and branch back to check. Use 13 in $t2 as a terminating condition
#

la $t4, strDragon
li $t2, 13
b check

dragFirstVal:
#
#Store strDragon in $t3
#

la $t3, strDragon
move $t2, $t1
b check

sword:
#
#Check if $t3 has been used, branch to swordFirstVal if not
#

beqz $t3, swordFirstVal

swordSecondVal:
#
#Store strSword in $t4 and branch back to check
#

la $t4, strSword
li $t2, 13
b check

swordFirstVal:
#
#Store strSword in $t3
#

la $t3, strSword
move $t2, $t1
b check

orc:
#
#Check if $t3 has been used, branch to orcFirstVal if not
#

beqz $t3, orcFirstVal

orcSecondVal:
#
#Store strOrc in $t4 and branch back to check
#

la $t4, strOrc
li $t2, 13
b check

orcFirstVal:
#
#Store strOrc in $t3
#

la $t3, strOrc
move $t2, $t1
b check

display:
#
#Output result of first die
#

la $a0, strFirstDie
li $v0, 4
syscall
move $a0, $t3
li $v0, 4
syscall

#
#Output result of second die
#

la $a0, strSecondDie
li $v0, 4
syscall
move $a0, $t4
li $v0, 4
syscall

end:
#
# System call code for exit = 10
#

li $v0, 10
syscall
