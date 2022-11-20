using System;
using System.Collections.Generic;
using System.Text;

namespace TaskB
{
    public class GraphNode<T> where T : IComparable
    {
        private T id; // data stored in the node (“id” of the  node).
        private LinkedList<T> adjList; // adjacency list of the node
                                       //Use LinkedList from C#

        // constructor 
        public GraphNode(T id)
        {
            this.id = id;
            adjList = new LinkedList<T>();
        }

        // set and get the data stored in the node 
        public T ID
        {
            get { return id; }
            set { id = value; }
        }


        //add a directed edge from “this” node to the node "to”
        public void AddEdge(GraphNode<T> to)
        {
            bool exists = false;
            foreach (T item in adjList)
            {
                if (item.CompareTo(to.ID) == 0)
                {
                    exists = true;
                }
            }
            if (!exists)
            {
                adjList.AddLast(to.ID);
            }
        }

        public void RemoveEdge(GraphNode<T> to)
        {
            adjList.Remove(to.ID);
        }

        // returns the adjacent list of the node
        public LinkedList<T> GetAdjList()
        {
            return adjList;
        }
    }

}
