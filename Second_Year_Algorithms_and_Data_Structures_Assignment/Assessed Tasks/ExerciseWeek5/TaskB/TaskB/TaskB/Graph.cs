using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;

namespace TaskB
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
            // to be completed
            foreach(GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(id) == 0)
                {
                    return n;
                }
            }
            return null;
            //Hint: Search through the list of nodes for a node with this id
        }



        // Add a directed edge between the node with id "from" and the node with id “to” 
        //doesn't automatically add nodes if they don't exist, due to the context of nodes being airports
        public void AddEdge(T from, T to)
        {
            GraphNode<T> toNode = GetNodeByID(to);
            GraphNode<T> fromNode = GetNodeByID(from);
            if (toNode != null & fromNode != null)
            {
                fromNode.AddEdge(toNode);
            }
        }

        public void RemoveEdge(T from, T to)
        {
            GraphNode<T> toNode = GetNodeByID(to);
            GraphNode<T> fromNode = GetNodeByID(from);
            if (toNode != null & fromNode != null)
            {
                fromNode.RemoveEdge(toNode);
            }
        }

        //Perform a DFS traversal starting at the node with id “startID”
        //leaving a list of visited id’s in the visited list. 

        public void DepthFirstTraverse(T startID, ref List<T> visited)
        {
            LinkedList<T> adj;
            Stack<T> toVisit = new Stack<T>();
            bool match = false;

            GraphNode<T> current = GetNodeByID(startID);

            toVisit.Push(startID);
            if (current != null)
            {
                while (toVisit.Count != 0)
                {
                    current = GetNodeByID(toVisit.Pop());
                    visited.Add(current.ID);

                    adj = current.GetAdjList();
                    foreach (T item in adj)
                    {
                        foreach (T node in visited)
                        {
                            if (item.CompareTo(node) == 0)
                            {
                                match = true;
                            }
                        }
                        if (!match)
                        {
                            foreach (T node in toVisit)
                            {
                                if (item.CompareTo(node) == 0)
                                {
                                    match = true;
                                }
                            }
                        }
                        if (!match)
                        {
                            toVisit.Push(item);
                        }
                        match = false;
                    }
                   
                }
            }

        }

        
        //Perform a BFS traversal starting at the node with id “startID”
        //leaving a list of visited id’s in the visited list. 

        public void BreadthFirstTraverse(T startID, ref List<T> visited)
        {
            LinkedList<T> adj;
            Queue<T> toVisit = new Queue<T>();
            GraphNode<T> current = GetNodeByID(startID);
            bool match = false;

            toVisit.Enqueue(startID);
            if (current != null)
            {
                while (toVisit.Count != 0)
                {
                    current = GetNodeByID(toVisit.Dequeue());
                    visited.Add(current.ID);
                    adj = current.GetAdjList();
                    foreach (T item in adj)
                    {
                        foreach (T node in visited)
                        {
                            if (item.CompareTo(node) == 0)
                            {
                                match = true;
                            }
                        }
                        if (!match)
                        {
                            foreach (T node in toVisit)
                            {
                                if (item.CompareTo(node) == 0)
                                {
                                    match = true;
                                }
                            }
                        }
                        if (!match)
                        {
                            toVisit.Enqueue(item);
                        }
                        match = false;
                    }
                }
            }
        }
        
        // Return a list which contains the id's of the nodes that are the mother
        
        public List<T> MotherVertex()
        {
            List<T> mothers = new List<T>();
            foreach (GraphNode<T> n in nodes)
            {
                List<T> visited = new List<T>();
                DepthFirstTraverse(n.ID, ref visited);
                if (visited.Count == NumNodesGraph())
                {
                    mothers.Add(n.ID);
                }
            }
            return mothers;
        }

        

    }

}
