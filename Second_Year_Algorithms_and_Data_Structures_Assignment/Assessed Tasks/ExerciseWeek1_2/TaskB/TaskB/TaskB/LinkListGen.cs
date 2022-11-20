using System;
using System.Collections.Generic;
using System.Text;

namespace TaskB
{
    class LinkListGen<T> where T : IComparable
    {
        private LinkGen<T> list = null; //default value – empty list

        public void AddItem(T item) //add item to front of list
        {
            list = new LinkGen<T>(item, list);

        }

        public string DisplayItems() //write items to string and return
        {
            LinkGen<T> temp = list;
            string buffer = "";
            while (temp != null) // move one link and add data to the buffer
            {
                buffer = buffer + temp.Data + "\n";
                temp = temp.Next;
            }
            return buffer;
        }

        public int NumberOfItems() // returns number of items in list
        {
            LinkGen<T> temp = list;
            int count = 0;
            while (temp != null) // move one link and add 1 to count
            {
                count++;
                temp = temp.Next;
            }
            return count;
        }

        public bool IsPresentItem(int item)
        {
            LinkGen<T> temp = list;
            while (temp != null)
            {
                if (item.CompareTo(temp.Data) == 0)
                {
                    return true;
                }
                temp = temp.Next;
            }
            return false;
        }
        public void AppendItem(T item)
        {
            LinkGen<T> temp = list;

            if (temp == null)
            {
                list = new LinkGen<T>(item);
            }
            else
            {
                while (temp.Next != null)
                {
                    temp = temp.Next;
                }
                temp.Next = new LinkGen<T>(item);
            }

        }
        public void RemoveItem(T item)
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();

            while (temp != null)
            {
                if (item.CompareTo(temp.Data) != 0)
                {
                    newList.AppendItem(temp.Data);
                }
                temp = temp.Next;
            }
            list = newList.list;

        }

        public void Concat(LinkListGen<T> list2)
        {
            LinkGen<T> temp = list2.list;
            while (temp != null)
            {
                AppendItem(temp.Data);
                temp = temp.Next;
            }

        }

        public void Copy(LinkListGen<T> list2)
        {
            LinkGen<T> temp = list;
            while(temp != null)
            {
                RemoveItem(temp.Data);
                temp = temp.Next;
            }
            Concat(list2);
        }

        public void InsertInOrder(T item)
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();
            bool added = false;
            if (list == null)
            {
                AddItem(item);
                added = true;
            }
            else
            {
                while (temp != null)
                {
                    if (item.CompareTo(temp.Data) > 0 | added)
                    {
                        newList.AppendItem(temp.Data);
                    }
                    else if (!added)
                    {
                        newList.AppendItem(item);
                        newList.AppendItem(temp.Data);
                        added = true;
                    }
                    temp = temp.Next;
                }
                if (!added)
                {
                    newList.AppendItem(item);
                    added = true;
                }
                list = newList.list;
            }
        }

        public void Sort()
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();

            while (temp != null)
            {
                newList.InsertInOrder(temp.Data);
                temp = temp.Next;
            }
            list = newList.list;
        }

        public T Find(T itemToFind)
        {
            LinkGen<T> temp = list;
            while (temp != null)
            {
                if (itemToFind.CompareTo(temp.Data) == 0)
                {
                    return temp.Data;
                }
                temp = temp.Next;
            }
            return default(T);
        }

    }
}
