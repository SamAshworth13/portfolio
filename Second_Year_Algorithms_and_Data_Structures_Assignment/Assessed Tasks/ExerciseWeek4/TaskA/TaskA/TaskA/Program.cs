using System;

namespace TaskA
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] titles = {"Writing Solid Code",
                "Objects First","Programming Gems",
                "Head First Java","The C Programming Language",
                "Mythical Man Month","The Art of Programming",
                "Coding Complete","Design Patterns",
                "ZZ"};
            string[] authors = { "Maguire", "Kolling", "Bentley", "Sierra", "Richie", "Brooks", "Knuth", "McConnal", "Gamma", "Weiss" };
            string[] isbns = { "948343", "849328493", "38948932", "394834342", "983492389", "84928334", "4839455", "21331322", "348923948", "43893284",
                "9483294", "9823943" };

            Book[] library = new Book[10];


            // fill an array of books
            for (int i = 0; i < library.Length; i++)
            {
                library[i] = new Book(isbns[i], titles[i], authors[i]);

            }

            SelectSortGen(library);
            DisplayArray(library);
            Console.WriteLine("\n");

            int[] intArray = { 1, 7, 4, 12, 16, 3 };
            SelectSortGen(intArray);
            DisplayArray(intArray);
            Console.WriteLine("\n");

            string[] strArray = { "pear", "banana", "apple", "cherry", "peach", "orange" };
            SelectSortGen(strArray);
            DisplayArray(strArray);
            Console.ReadKey();
        }

        static public void SelectSortGen<T>(T[] a) where T: IComparable
        {
            for (int i = 0; i < a.Length - 1; i++)
            {
                int smallest = i;
                for (int j = i + 1; j < a.Length; j++)
                {
                    if (a[j].CompareTo(a[smallest]) < 0)
                        smallest = j;
                }
                swap(ref a[i], ref a[smallest]);
            }
        }

        static void swap<T>(ref T x, ref T y)
        {
            T temp = x;
            x = y;
            y = temp;
        }

        static public void DisplayArray<T>(T[] a)
        {
            for (int i = 0; i < a.Length; i++)
            {
                Console.Write(a[i] + "\n");
            }
        }
    }
}
