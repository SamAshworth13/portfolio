using System;
using System.Collections.Generic;
using System.Text;

namespace Equals_And_SubTree_Test
{
    class BSTree<T> : BinTree<T> where T : IComparable
    {  //root declared as protected in Parent Class – Binary Tree
        public BSTree()
        {
            root = null;
        }

        public void InsertItem(T item)
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
        }


        public Boolean Contains(T item)
        {
            return contains(item, root);
        }

        private Boolean contains(T item, Node<T> tree)
        {
            if (tree == null)
            {
                return false;
            }
            else if (item.CompareTo(tree.Data) == 0)
            {
                return true;
            }
            else if (item.CompareTo(tree.Data) < 0)
            {
                return (contains(item, tree.Left));
            }
            return (contains(item, tree.Right));
        }

        public void RemoveItem(T item)
        {
            removeItem(item, ref root);
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

        protected T leastItem(Node<T> tree)
        {
            if (tree.Left == null)
            {
                return tree.Data;
            }
            else
            {
                return leastItem(tree.Left);
            }
        }

        public void CopyTo(ref BSTree<T> tree)
        {
            //copies the nodes of this BSTree object
            //into a tree maintaining the same structure and order.

            //clear out copyTree
            tree.empty();
            copyTo(root, ref tree);
        }

        private void copyTo(Node<T> thisTree, ref BSTree<T> copyTree)
        {

            //use preOrder traversal
            if (thisTree != null)
            {
                copyTree.InsertItem(thisTree.Data);
                copyTo(thisTree.Left, ref copyTree);
                copyTo(thisTree.Right, ref copyTree);
            }


        }

        private void empty()
        {
            while (root != null)
            {
                removeItem(root.Data, ref root);
            }
        }

        public bool Equals(BSTree<T> tree)
        {
            //returns true if this BSTree object contains the all same data as
            //tree with the same structure and ordering of data.
            return equals(root, tree.root);


        }
        private bool equals(Node<T> tree, Node<T> sub)
        {
            // base cases
            if (tree == null & sub == null)
            {
                return true;
            }

            if (tree == null || sub == null)
            {
                return false;
            }

            //check if the current values are equal and recursively check the rest of the tree is equal
            return ((tree.Data.CompareTo(sub.Data) == 0) & equals(tree.Left, sub.Left) & equals(tree.Right, sub.Right));
        }

        public bool SubTree(BSTree<T> tree)
        {
            //returns true if this BSTree object contains the subtree tree. 
            //A subtree of a tree T is a tree consisting of a node in T and all
            // of its descendants in T
            return subTree(root, tree.root);

        }

        private bool subTree(Node<T> tree, Node<T> sub)
        {
            // base cases
            if (sub == null)
            {
                return true;
            }

            if (tree == null)
            {
                return false;
            }

            if (equals(tree, sub))
            {
                return true;
            }

            //recursively check to the left and right of tree to see if matches sub
            return (subTree(tree.Left, sub) | subTree(tree.Right, sub));




        }
    }

}
