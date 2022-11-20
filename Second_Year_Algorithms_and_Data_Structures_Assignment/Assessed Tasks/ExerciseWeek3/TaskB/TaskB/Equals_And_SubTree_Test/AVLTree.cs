using System;
using System.Collections.Generic;
using System.Text;

namespace Equals_And_SubTree_Test
{
    class AVLTree<T> : BSTree<T> where T : IComparable
    {
        public new void InsertItem(T item)
        {
            insertItem(item, ref root);
        }

        private void insertItem(T item, ref Node<T> tree)
        {
            if (tree == null)
            {
                tree = new Node<T>(item);
            }
            else if (item.CompareTo(tree.Data) < 0)
            {
                insertItem(item, ref tree.Left);
            }
            else if (item.CompareTo(tree.Data) > 0)
            {
                insertItem(item, ref tree.Right);
            }

            tree.BalanceFactor = height(tree.Left) - height(tree.Right);

            if (tree.BalanceFactor <= -2)
            {
                rotateLeft(ref tree);
            }
            if (tree.BalanceFactor >= 2)
            {
                rotateRight(ref tree);
            }

        }

        private void rotateLeft(ref Node<T> tree)
        {
            if (tree.Right.BalanceFactor > 0) //double rotate
            {
                rotateRight(ref tree.Right);
            }

            Node<T> oldRoot = tree;
            Node<T> newRoot = tree.Right;

            oldRoot.Right = newRoot.Left;
            newRoot.Left = oldRoot;
            tree = newRoot;


        }

        private void rotateRight(ref Node<T> tree)
        {
            if (tree.Left.BalanceFactor < 0) //double rotate
            {
                rotateLeft(ref tree.Left);
            }

            Node<T> oldRoot = tree;
            Node<T> newRoot = tree.Left;

            oldRoot.Left = newRoot.Right;
            newRoot.Right = oldRoot;
            tree = newRoot;
        }

        public new void RemoveItem(T item)
        {
            removeItem(item, ref root);
            balanceTree(ref root);
            
        }

        private void removeItem(T item, ref Node<T> tree)
        {
            if (tree != null)
            {
                if (item.CompareTo(tree.Data) == 0)
                {
                    if (tree.Left == null)
                    {
                        tree = tree.Right;
                    }
                    else if (tree.Right == null)
                    {
                        tree = tree.Left;
                    }
                    else
                    {
                        tree.Data = leastItem(tree.Right);  //find smallest item to the right
                        removeItem(tree.Data, ref tree.Right);

                        
                    }
                }
                else if (item.CompareTo(tree.Data) < 0)
                {
                    removeItem(item, ref tree.Left);
                }
                else
                {
                    removeItem(item, ref tree.Right);
                }

            }


            
        }

        private void balanceTree(ref Node<T> tree)
        {
            //go through entire tree updating the balance factor
            if (tree != null)
            {
                tree.BalanceFactor = height(tree.Left) - height(tree.Right);

                if (tree.BalanceFactor <= -2)
                {
                    rotateLeft(ref tree);
                }
                if (root.BalanceFactor >= 2)
                {
                    rotateRight(ref tree);
                }
            }
            if (tree.Left != null)
            {
                balanceTree(ref tree.Left);
            }
            if (tree.Right != null)
            {
                balanceTree(ref tree.Left);
            }
        }
    }

}
