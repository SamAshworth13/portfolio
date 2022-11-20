using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TaskA
{
    public partial class Form1 : Form
    {
        Graph<char> myGraph = new Graph<char>();
        public Form1()
        {
            InitializeComponent();
        }

        private void insertNodeButton_Click(object sender, EventArgs e)
        {
            myGraph.AddNode(idTextBox.Text[0]);
            nodeCountLabel.Text = "Node Count: " + myGraph.NumNodesGraph();
        }

        private void insertEdgeButton_Click(object sender, EventArgs e)
        {
            myGraph.AddEdge(fromTextBox.Text[0], toTextBox.Text[0]);
            edgeCountLabel.Text = "Edge Count: " + myGraph.NumEdgesGraph();
            nodeCountLabel.Text = "Node Count: " + myGraph.NumNodesGraph();
        }
    }
}
