using System;
using System.IO; //Allows program to read files

namespace String_BSTree
{
    class Program
    {
        static void Main(string[] args)
        {
            BSTree<string> myTree = new BSTree<string>();
            readFile("textFile.txt", ref myTree);
            Console.WriteLine("Number of unique words: {0}", myTree.Count());
            Console.WriteLine("Height of myTree: {0}", myTree.Height());
            Console.ReadKey();
        }

        static void readFile(string fileName, ref BSTree<string> tree)
        {
            const int MAX_FILE_LINES = 50000;
            string[] AllLines = new string[MAX_FILE_LINES];

            //reads from bin/DEBUG subdirectory of project directory
            AllLines = File.ReadAllLines(fileName);

            foreach (string line in AllLines)
            {
                //split words using space , . ?
                string[] words = line.Split(' ', ',', '.', '?', ';', ':', '!');
                foreach (string word in words)
                    if (word != "")
                    {
                        tree.InsertItem(word);
                    }
            }
        }

    }
}
