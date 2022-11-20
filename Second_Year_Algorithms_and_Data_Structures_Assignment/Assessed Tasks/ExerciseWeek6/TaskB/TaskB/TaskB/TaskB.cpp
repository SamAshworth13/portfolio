// TaskB.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
//don't need include <string> as visual studio has it automatically
using namespace std;
void recombination(string, string, string);
int main()
{
    string str1, str2, str3;
    cout << "Enter a string containing only characters: 'A', 'T', 'C', and 'G':" << endl;
    cin >> str1;
    cout << "Enter another different string using the same rules:" << endl;
    cin >> str2;
    cout << "Enter a string of two different characters from the same set:\n(This will be used to recombine the previous strings)" << endl;
    cin >> str3;
    
    cout << "All possible recombinations:\n" << endl;
    recombination(str1, str2, str3);
    return 0;
}

void recombination(string s1, string s2, string s3)
{
    int pos1, pos2;
    string s1_l, s1_r, s2_l, s2_r;
    string out = "";


    //stopping condition pos == string::npos
    pos1 = s1.find(s3);
    while (pos1 != string::npos)
    {
        //split s1
        s1_l = s1.substr(0, pos1);
        s1_r = s1.substr(pos1 + s3.length(), s1.length() - (pos1 + s3.length()));

        pos2 = s2.find(s3);
        while (pos2 != string::npos)
        {
            //split s2
            s2_l = s2.substr(0, pos2);
            s2_r = s2.substr(pos2 + s3.length(), s2.length() - (pos2 + s3.length()));

            //combine s1_l, s3 and s2_r
            out = s1_l + s3 + s2_r;
            cout << out << endl;
            out = "";

            //combine s2_l, s3 and s2_l
            out = s2_l + s3 + s1_r;
            cout << out << endl;
            out = "";

            //check the s2_r for any more occurrences of s3
            pos2 = s2_r.find(s3);
            if (pos2 != string::npos)
            {
                //if s3 found, adjust pos2 to be position in s2 instead of s2_r
                pos2 += s2_l.length() + s3.length();
            }
            //if s3 not found, pos2 = npos, exit loop
        }
        //check the s1_r for any more occurrences of s3
        pos1 = s1_r.find(s3);
        if (pos1 != string::npos)
        {
            //if s3 found, adjust pos1 to be position in s1 instead of s1_r
            pos1 += s1_l.length() + s3.length();
        }
        //if s3 not found, pos1 = npos, exit loop
    }

}

