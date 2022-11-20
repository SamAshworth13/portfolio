// TaskA.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <cstring>

using namespace std;
void substitute(char*, char, char);
int main()
{
    char str[50];
    char c1, c2;
    cout << "This program will accept a string and replace a specific character with another,\nas long as that character is followed by '!'" << endl;
    cout << "Enter a string (up to 50 characters) which contains '!': " << endl;
    cin >> str;
    char* ptr = &str[0];
    cout << "Enter a character (which appears directly before '!') to search for within the string: " << endl;
    cin >> c1;
    cout << "Enter a character to replace it with: " << endl;
    cin >> c2;
    cout << "Original: " << str << endl;
    substitute(ptr, c1, c2);
    cout << "Substituted: " << str << endl;
    return 0;
}

void substitute(char* s, char c1, char c2)
{
    for (int i = 0; i < 50; i++)
    {
        if (*(s + i) == c1)
        {
            if (*(s + i + 1) == '!')
            {
                *(s + i) = c2;
            }
        }

    }

}

