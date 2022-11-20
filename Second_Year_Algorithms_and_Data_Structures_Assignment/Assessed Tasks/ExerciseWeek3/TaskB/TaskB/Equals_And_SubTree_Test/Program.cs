using System;

namespace Equals_And_SubTree_Test
{
    class Program
    {
        static void Main(string[] args)
        {
            AVLTree<int> myTree = new AVLTree<int>();
            AVLTree<int> myTree2 = new AVLTree<int>();

            Console.WriteLine("Test 1: \n");

            Console.WriteLine("myTree in order: {0}", myTree.InOrder());
            Console.WriteLine("myTree Height: {0}", myTree.Height());

            Console.WriteLine("myTree2 in order: {0}", myTree2.InOrder());
            Console.WriteLine("myTree2 Height: {0}", myTree2.Height());

            //tests for Equals with myTree and myTree2 being empty, expect Both True
            Console.WriteLine("myTree = myTree2?: {0}", myTree.Equals(myTree2));
            Console.WriteLine("myTree2 = myTree?: {0}", myTree2.Equals(myTree));

            //tests for SubTree with myTree and myTree2 being empty, expect Both True
            Console.WriteLine("Is myTree2 a subtree of myTree?: {0}", myTree.SubTree(myTree2));
            Console.WriteLine("Is myTree a subtree of myTree2?: {0}", myTree2.Equals(myTree));

            Console.WriteLine("----------------------------------- \n\n");

            Console.WriteLine("Test 2: \n");

            myTree.InsertItem(15);
            myTree.InsertItem(25);
            myTree.InsertItem(10);
            myTree.InsertItem(20);
            myTree.InsertItem(23);
            myTree.InsertItem(17);

            
            Console.WriteLine("myTree in order: {0}", myTree.InOrder());
            Console.WriteLine("myTree Height: {0}", myTree.Height());

            Console.WriteLine("myTree2 in order: {0}", myTree2.InOrder());
            Console.WriteLine("myTree2 Height: {0}", myTree2.Height());

            //tests for Equals with myTree2 being empty and myTree not empty, expect Both False
            Console.WriteLine("myTree = myTree2?: {0}", myTree.Equals(myTree2));
            Console.WriteLine("myTree2 = myTree?: {0}", myTree2.Equals(myTree));

            //tests for SubTree with myTree2 being empty and myTree not empty, expect True, False
            Console.WriteLine("Is myTree2 a subtree of myTree?: {0}", myTree.SubTree(myTree2));
            Console.WriteLine("Is myTree a subtree of myTree2?: {0}", myTree2.Equals(myTree));

            Console.WriteLine("----------------------------------- \n\n");

            Console.WriteLine("Test 3: \n");

            myTree2.InsertItem(15);
            myTree2.InsertItem(25);
            myTree2.InsertItem(10);
            myTree2.InsertItem(20);
            myTree2.InsertItem(23);
            myTree2.InsertItem(17);

            Console.WriteLine("myTree in order: {0}", myTree.InOrder());
            Console.WriteLine("myTree Height: {0}", myTree.Height());

            Console.WriteLine("myTree2 in order: {0}", myTree2.InOrder());
            Console.WriteLine("myTree2 Height: {0}", myTree2.Height());

            //tests for Equals with myTree2 and myTree containing the same data, expect Both True
            Console.WriteLine("myTree = myTree2?: {0}", myTree.Equals(myTree2));
            Console.WriteLine("myTree2 = myTree?: {0}", myTree2.Equals(myTree));

            //tests for SubTree with myTree2 and myTree containing the same data, expect Both True
            Console.WriteLine("Is myTree2 a subtree of myTree?: {0}", myTree.SubTree(myTree2));
            Console.WriteLine("Is myTree a subtree of myTree2?: {0}", myTree2.Equals(myTree));

            Console.WriteLine("----------------------------------- \n\n");

            Console.WriteLine("Test 4: \n");

            myTree.InsertItem(3);

            Console.WriteLine("myTree in order: {0}", myTree.InOrder());
            Console.WriteLine("myTree Height: {0}", myTree.Height());

            Console.WriteLine("myTree2 in order: {0}", myTree2.InOrder());
            Console.WriteLine("myTree2 Height: {0}", myTree2.Height());

            //tests for Equals with myTree2 and myTree containing almost the same data, expect Both False
            Console.WriteLine("myTree = myTree2?: {0}", myTree.Equals(myTree2));
            Console.WriteLine("myTree2 = myTree?: {0}", myTree2.Equals(myTree));

            //tests for SubTree with myTree2 and myTree containing almost the same data, expect Both False
            Console.WriteLine("Is myTree2 a subtree of myTree?: {0}", myTree.SubTree(myTree2));
            Console.WriteLine("Is myTree a subtree of myTree2?: {0}", myTree2.Equals(myTree));

            Console.WriteLine("----------------------------------- \n\n");

            Console.WriteLine("Test 5: \n");

            myTree2 = new AVLTree<int>();
            myTree2.InsertItem(15);
            myTree2.InsertItem(10);
            myTree2.InsertItem(17);
            myTree2.InsertItem(3);

            Console.WriteLine("myTree in order: {0}", myTree.InOrder());
            Console.WriteLine("myTree Height: {0}", myTree.Height());

            Console.WriteLine("myTree2 in order: {0}", myTree2.InOrder());
            Console.WriteLine("myTree2 Height: {0}", myTree2.Height());

            //tests for Equals where myTree2 is a subtree of myTree, expect Both False
            Console.WriteLine("myTree = myTree2?: {0}", myTree.Equals(myTree2));
            Console.WriteLine("myTree2 = myTree?: {0}", myTree2.Equals(myTree));

            //tests for SubTree where myTree2 is a subtree of myTree, expect True, False
            Console.WriteLine("Is myTree2 a subtree of myTree?: {0}", myTree.SubTree(myTree2));
            Console.WriteLine("Is myTree a subtree of myTree2?: {0}", myTree2.Equals(myTree));

            Console.ReadKey();

        }
    }
}
