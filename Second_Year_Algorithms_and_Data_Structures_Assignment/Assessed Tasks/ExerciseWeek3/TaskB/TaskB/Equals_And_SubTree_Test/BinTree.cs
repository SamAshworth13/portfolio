using System;
using System.Collections.Generic;
using System.Text;

namespace Equals_And_SubTree_Test
{
    class BinTree<T> where T : IComparable
    {
        protected Node<T> root;
        public BinTree()  //creates an empty tree
        {
            root = null;
        }

        public BinTree(Node<T> node)  //creates a tree with node as the root
        {
            root = node;
        }

        public string InOrder()
        {
            string buffer = ""; //clear buffer before starting
            return inOrder(root, ref buffer);
        }

        private string inOrder(Node<T> tree, ref string buffer)
        {
            if (tree != null)
            {
                inOrder(tree.Left, ref buffer);
                buffer += tree.Data.ToString() + " ";
                inOrder(tree.Right, ref buffer);
            }
            return buffer;
        }

        public string PreOrder()
        {
            string buffer = ""; //clear buffer before starting
            return preOrder(root, ref buffer);
        }

        private string preOrder(Node<T> tree, ref string buffer)
        {
            if (tree != null)
            {
                buffer += tree.Data.ToString() + " ";
                preOrder(tree.Left, ref buffer);
                preOrder(tree.Right, ref buffer);
            }
            return buffer;
        }

        public string PostOrder()
        {
            string buffer = ""; //clear buffer before starting
            return postOrder(root, ref buffer);
        }

        private string postOrder(Node<T> tree, ref string buffer)
        {
            if (tree != null)
            {
                postOrder(tree.Left, ref buffer);
                postOrder(tree.Right, ref buffer);
                buffer += tree.Data.ToString() + " ";
            }
            return buffer;
        }

        public T biggestItem()
        {
            return biggestItem(root);
        }

        private T biggestItem(Node<T> tree)
        {
            if (tree == null)
            {
                return default(T);
            }
            else
            {
                T big = biggestItem(tree.Left);
                if (tree.Data.CompareTo(big) > 0)
                {
                    big = tree.Data;
                }
                T rightBig = biggestItem(tree.Right);
                if (big.CompareTo(rightBig) > 0)
                {
                    return big;
                }
                return rightBig;
            }
        }


        public int Count()
        {
            return count(root);
        }


        private int count(Node<T> tree)
        {
            if (tree != null)
            {
                return count(tree.Left) + count(tree.Right) + 1;
            }
            return 0;
        }

        public int Height()
        {
            return height(root);
        }

        protected int height(Node<T> tree)
        {
            if (tree != null)
            {
                return max(height(tree.Left), height(tree.Right)) + 1;
            }
            return 0;
        }

        private int max(int x, int y)
        {
            if (x >= y)
            {
                return x;
            }
            return y;
        }

    }

}
