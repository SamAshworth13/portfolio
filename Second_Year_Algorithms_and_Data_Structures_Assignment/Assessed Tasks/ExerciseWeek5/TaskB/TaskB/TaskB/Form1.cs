using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TaskB
{
    public partial class Form1 : Form
    {
        Graph<string> myGraph = new Graph<string>();
        public Form1()
        {
            InitializeComponent();
        }

        private void addAirportButton_Click(object sender, EventArgs e)
        {
            myGraph.AddNode(airportCodeTextBox.Text);
            nodeCountLabel.Text = "Airport Count: " + myGraph.NumNodesGraph();
            updateMothers();
        }

        private void addConnectionButton_Click(object sender, EventArgs e)
        {
            myGraph.AddEdge(fromTextBox.Text, toTextBox.Text);
            edgeCountLabel.Text = "Connection Count: " + myGraph.NumEdgesGraph();
            updateMothers();
        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            myGraph.RemoveEdge(rmvFromTextBox.Text, rmvToTextBox.Text);
            edgeCountLabel.Text = "Connection Count: " + myGraph.NumEdgesGraph();
            updateMothers();
        }

        private void checkConnectionsButton_Click(object sender, EventArgs e)
        {
            List<string> visited = new List<string>();
            string displayStr = "Reachable Airports: ";
            myGraph.DepthFirstTraverse(connectionCheckTextBox.Text, ref visited);

            if (visited.Count == 0)
            {
                displayStr += "Airport Code Not Found.";
            }
            else
            {
                foreach (string item in visited)
                {
                    displayStr += item + " ";
                }
            }
            displayAirportsLabel.Text = displayStr;
        }

        private void updateMothers()
        {
            string displayStr = "";
            foreach (string str in myGraph.MotherVertex())
            {
                displayStr += str + " ";
            }
            displayMothersLabel.Text = displayStr;
            displayMothersLabel.Visible = true;
        }
    }
}
