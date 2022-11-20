using System;
using System.Collections.Generic;
using System.Text;

namespace TaskA
{
    public class Graph<T> where T : IComparable
    {
        
        // list of all the nodes in the graph. Use LinkedList from C#
        private LinkedList<GraphNode<T>> nodes;


        // constructor – set nodes to new empty list
        public Graph()
        {
            nodes = new LinkedList<GraphNode<T>>();
        }


        // only returns true if the graph’s list of nodes is empty
        public bool IsEmptyGraph()
        {
            return nodes.Count == 0;
        }


        // returns the total number of nodes present in the graph
        public int NumNodesGraph()
        {
            return nodes.Count;           

        }

        
        // returns the total number of edges present in the graph
        public int NumEdgesGraph()
        {
            int edgeCount = 0;
            LinkedList<T> curAdjList = new LinkedList<T>();


            foreach (GraphNode<T> n in nodes)
            {
                curAdjList = n.GetAdjList();
                edgeCount += curAdjList.Count;
            }

            return edgeCount;

        }



        // only returns true if node is present in the graph
        public bool ContainsGraph(GraphNode<T> node)
        {
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(node.ID) == 0)
                    return true;
            }
            return false;

        }

        
        // only returns true if nodes “from “ and “to” are adjacent

        public bool IsAdjacent(GraphNode<T> from, GraphNode<T> to)
        {
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(from.ID) == 0)
                {
                    bool b = n.GetAdjList().Contains(to.ID);
                    return b;
                }
            }
            return false;
        }

        
        // add a new node (with this “id”) to the list of nodes of the graph
        public void AddNode(T id)
        {
            bool isDuplicate = false;
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(id) == 0)
                {
                    isDuplicate = true;
                }
            }
            if (!isDuplicate)
            {
                GraphNode<T> newNode = new GraphNode<T>(id);
                nodes.AddLast(newNode);
            }

        }

        
        //returns the node with this id
        public GraphNode<T> GetNodeByID(T id)
        {
            foreach(GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(id) == 0)
                {
                    return n;
                }
            }
            return null;
        }



        // Add a directed edge between the node with id "from" and the node with id “to” 
        //altered to add the nodes to and from if they don't currently exist
        public void AddEdge(T from, T to)
        {
            GraphNode<T> toNode = GetNodeByID(to);
            GraphNode<T> fromNode = GetNodeByID(from);
            if (toNode != null & fromNode != null)
            {
                fromNode.AddEdge(toNode);
            }
            else
            {
                if (toNode == null)
                {
                    AddNode(to);
                }
                if (fromNode == null)
                {
                    AddNode(from);
                }
                AddEdge(from, to);
            }
        }

        
    }

}
