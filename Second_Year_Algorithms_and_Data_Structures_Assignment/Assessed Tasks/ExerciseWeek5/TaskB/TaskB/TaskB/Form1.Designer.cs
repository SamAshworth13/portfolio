namespace TaskB
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.addAirportLabel = new System.Windows.Forms.Label();
            this.addAirportButton = new System.Windows.Forms.Button();
            this.airportCodeTextBox = new System.Windows.Forms.TextBox();
            this.airportCodeLabel = new System.Windows.Forms.Label();
            this.fromTextBox = new System.Windows.Forms.TextBox();
            this.toTextBox = new System.Windows.Forms.TextBox();
            this.addConnectionButton = new System.Windows.Forms.Button();
            this.addConnectionLabel = new System.Windows.Forms.Label();
            this.fromLabel = new System.Windows.Forms.Label();
            this.toLabel = new System.Windows.Forms.Label();
            this.connectionCheckTextBox = new System.Windows.Forms.TextBox();
            this.connectionCheckLabel = new System.Windows.Forms.Label();
            this.checkCodeLabel = new System.Windows.Forms.Label();
            this.checkConnectionsButton = new System.Windows.Forms.Button();
            this.displayAirportsLabel = new System.Windows.Forms.Label();
            this.allReachableLabel = new System.Windows.Forms.Label();
            this.displayMothersLabel = new System.Windows.Forms.Label();
            this.rmvToLabel = new System.Windows.Forms.Label();
            this.rmvFromLabel = new System.Windows.Forms.Label();
            this.removeLabel = new System.Windows.Forms.Label();
            this.removeButton = new System.Windows.Forms.Button();
            this.rmvToTextBox = new System.Windows.Forms.TextBox();
            this.rmvFromTextBox = new System.Windows.Forms.TextBox();
            this.nodeCountLabel = new System.Windows.Forms.Label();
            this.edgeCountLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // addAirportLabel
            // 
            this.addAirportLabel.AutoSize = true;
            this.addAirportLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addAirportLabel.Location = new System.Drawing.Point(12, 9);
            this.addAirportLabel.Name = "addAirportLabel";
            this.addAirportLabel.Size = new System.Drawing.Size(93, 20);
            this.addAirportLabel.TabIndex = 0;
            this.addAirportLabel.Text = "Add Airport:";
            // 
            // addAirportButton
            // 
            this.addAirportButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addAirportButton.Location = new System.Drawing.Point(330, 56);
            this.addAirportButton.Name = "addAirportButton";
            this.addAirportButton.Size = new System.Drawing.Size(65, 26);
            this.addAirportButton.TabIndex = 1;
            this.addAirportButton.Text = "Add";
            this.addAirportButton.UseVisualStyleBackColor = true;
            this.addAirportButton.Click += new System.EventHandler(this.addAirportButton_Click);
            // 
            // airportCodeTextBox
            // 
            this.airportCodeTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.airportCodeTextBox.Location = new System.Drawing.Point(181, 56);
            this.airportCodeTextBox.Name = "airportCodeTextBox";
            this.airportCodeTextBox.Size = new System.Drawing.Size(109, 26);
            this.airportCodeTextBox.TabIndex = 2;
            // 
            // airportCodeLabel
            // 
            this.airportCodeLabel.AutoSize = true;
            this.airportCodeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.airportCodeLabel.Location = new System.Drawing.Point(12, 59);
            this.airportCodeLabel.Name = "airportCodeLabel";
            this.airportCodeLabel.Size = new System.Drawing.Size(102, 20);
            this.airportCodeLabel.TabIndex = 3;
            this.airportCodeLabel.Text = "Airport Code:";
            // 
            // fromTextBox
            // 
            this.fromTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.fromTextBox.Location = new System.Drawing.Point(181, 167);
            this.fromTextBox.Name = "fromTextBox";
            this.fromTextBox.Size = new System.Drawing.Size(109, 26);
            this.fromTextBox.TabIndex = 4;
            // 
            // toTextBox
            // 
            this.toTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.toTextBox.Location = new System.Drawing.Point(181, 214);
            this.toTextBox.Name = "toTextBox";
            this.toTextBox.Size = new System.Drawing.Size(109, 26);
            this.toTextBox.TabIndex = 5;
            // 
            // addConnectionButton
            // 
            this.addConnectionButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addConnectionButton.Location = new System.Drawing.Point(330, 214);
            this.addConnectionButton.Name = "addConnectionButton";
            this.addConnectionButton.Size = new System.Drawing.Size(65, 26);
            this.addConnectionButton.TabIndex = 6;
            this.addConnectionButton.Text = "Add";
            this.addConnectionButton.UseVisualStyleBackColor = true;
            this.addConnectionButton.Click += new System.EventHandler(this.addConnectionButton_Click);
            // 
            // addConnectionLabel
            // 
            this.addConnectionLabel.AutoSize = true;
            this.addConnectionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.addConnectionLabel.Location = new System.Drawing.Point(12, 121);
            this.addConnectionLabel.Name = "addConnectionLabel";
            this.addConnectionLabel.Size = new System.Drawing.Size(127, 20);
            this.addConnectionLabel.TabIndex = 7;
            this.addConnectionLabel.Text = "Add Connection:";
            // 
            // fromLabel
            // 
            this.fromLabel.AutoSize = true;
            this.fromLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.fromLabel.Location = new System.Drawing.Point(12, 170);
            this.fromLabel.Name = "fromLabel";
            this.fromLabel.Size = new System.Drawing.Size(153, 20);
            this.fromLabel.TabIndex = 8;
            this.fromLabel.Text = "From (Airport Code):";
            // 
            // toLabel
            // 
            this.toLabel.AutoSize = true;
            this.toLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.toLabel.Location = new System.Drawing.Point(12, 217);
            this.toLabel.Name = "toLabel";
            this.toLabel.Size = new System.Drawing.Size(134, 20);
            this.toLabel.TabIndex = 9;
            this.toLabel.Text = "To (Airport Code):";
            // 
            // connectionCheckTextBox
            // 
            this.connectionCheckTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.connectionCheckTextBox.Location = new System.Drawing.Point(181, 360);
            this.connectionCheckTextBox.Name = "connectionCheckTextBox";
            this.connectionCheckTextBox.Size = new System.Drawing.Size(109, 26);
            this.connectionCheckTextBox.TabIndex = 10;
            // 
            // connectionCheckLabel
            // 
            this.connectionCheckLabel.AutoSize = true;
            this.connectionCheckLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.connectionCheckLabel.Location = new System.Drawing.Point(12, 314);
            this.connectionCheckLabel.Name = "connectionCheckLabel";
            this.connectionCheckLabel.Size = new System.Drawing.Size(198, 20);
            this.connectionCheckLabel.TabIndex = 11;
            this.connectionCheckLabel.Text = "Check Reachable Airports:";
            // 
            // checkCodeLabel
            // 
            this.checkCodeLabel.AutoSize = true;
            this.checkCodeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.checkCodeLabel.Location = new System.Drawing.Point(12, 360);
            this.checkCodeLabel.Name = "checkCodeLabel";
            this.checkCodeLabel.Size = new System.Drawing.Size(120, 20);
            this.checkCodeLabel.TabIndex = 12;
            this.checkCodeLabel.Text = "Starting Airport:";
            // 
            // checkConnectionsButton
            // 
            this.checkConnectionsButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.checkConnectionsButton.Location = new System.Drawing.Point(330, 360);
            this.checkConnectionsButton.Name = "checkConnectionsButton";
            this.checkConnectionsButton.Size = new System.Drawing.Size(65, 26);
            this.checkConnectionsButton.TabIndex = 13;
            this.checkConnectionsButton.Text = "Check";
            this.checkConnectionsButton.UseVisualStyleBackColor = true;
            this.checkConnectionsButton.Click += new System.EventHandler(this.checkConnectionsButton_Click);
            // 
            // displayAirportsLabel
            // 
            this.displayAirportsLabel.AutoSize = true;
            this.displayAirportsLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.displayAirportsLabel.Location = new System.Drawing.Point(12, 415);
            this.displayAirportsLabel.Name = "displayAirportsLabel";
            this.displayAirportsLabel.Size = new System.Drawing.Size(153, 20);
            this.displayAirportsLabel.TabIndex = 14;
            this.displayAirportsLabel.Text = "Reachable Airports: ";
            // 
            // allReachableLabel
            // 
            this.allReachableLabel.AutoSize = true;
            this.allReachableLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.allReachableLabel.Location = new System.Drawing.Point(12, 496);
            this.allReachableLabel.Name = "allReachableLabel";
            this.allReachableLabel.Size = new System.Drawing.Size(330, 20);
            this.allReachableLabel.TabIndex = 15;
            this.allReachableLabel.Text = "Airports from which all others can be reached:";
            // 
            // displayMothersLabel
            // 
            this.displayMothersLabel.AutoSize = true;
            this.displayMothersLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.displayMothersLabel.Location = new System.Drawing.Point(12, 539);
            this.displayMothersLabel.Name = "displayMothersLabel";
            this.displayMothersLabel.Size = new System.Drawing.Size(0, 20);
            this.displayMothersLabel.TabIndex = 16;
            this.displayMothersLabel.Visible = false;
            // 
            // rmvToLabel
            // 
            this.rmvToLabel.AutoSize = true;
            this.rmvToLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rmvToLabel.Location = new System.Drawing.Point(433, 217);
            this.rmvToLabel.Name = "rmvToLabel";
            this.rmvToLabel.Size = new System.Drawing.Size(134, 20);
            this.rmvToLabel.TabIndex = 22;
            this.rmvToLabel.Text = "To (Airport Code):";
            // 
            // rmvFromLabel
            // 
            this.rmvFromLabel.AutoSize = true;
            this.rmvFromLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rmvFromLabel.Location = new System.Drawing.Point(433, 170);
            this.rmvFromLabel.Name = "rmvFromLabel";
            this.rmvFromLabel.Size = new System.Drawing.Size(153, 20);
            this.rmvFromLabel.TabIndex = 21;
            this.rmvFromLabel.Text = "From (Airport Code):";
            // 
            // removeLabel
            // 
            this.removeLabel.AutoSize = true;
            this.removeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.removeLabel.Location = new System.Drawing.Point(433, 121);
            this.removeLabel.Name = "removeLabel";
            this.removeLabel.Size = new System.Drawing.Size(157, 20);
            this.removeLabel.TabIndex = 20;
            this.removeLabel.Text = "Remove Connection:";
            // 
            // removeButton
            // 
            this.removeButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.removeButton.Location = new System.Drawing.Point(737, 214);
            this.removeButton.Name = "removeButton";
            this.removeButton.Size = new System.Drawing.Size(81, 26);
            this.removeButton.TabIndex = 19;
            this.removeButton.Text = "Remove";
            this.removeButton.UseVisualStyleBackColor = true;
            this.removeButton.Click += new System.EventHandler(this.removeButton_Click);
            // 
            // rmvToTextBox
            // 
            this.rmvToTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rmvToTextBox.Location = new System.Drawing.Point(602, 214);
            this.rmvToTextBox.Name = "rmvToTextBox";
            this.rmvToTextBox.Size = new System.Drawing.Size(109, 26);
            this.rmvToTextBox.TabIndex = 18;
            // 
            // rmvFromTextBox
            // 
            this.rmvFromTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rmvFromTextBox.Location = new System.Drawing.Point(602, 167);
            this.rmvFromTextBox.Name = "rmvFromTextBox";
            this.rmvFromTextBox.Size = new System.Drawing.Size(109, 26);
            this.rmvFromTextBox.TabIndex = 17;
            // 
            // nodeCountLabel
            // 
            this.nodeCountLabel.AutoSize = true;
            this.nodeCountLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nodeCountLabel.Location = new System.Drawing.Point(433, 9);
            this.nodeCountLabel.Name = "nodeCountLabel";
            this.nodeCountLabel.Size = new System.Drawing.Size(120, 20);
            this.nodeCountLabel.TabIndex = 26;
            this.nodeCountLabel.Text = "Airport Count: 0";
            // 
            // edgeCountLabel
            // 
            this.edgeCountLabel.AutoSize = true;
            this.edgeCountLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.edgeCountLabel.Location = new System.Drawing.Point(433, 43);
            this.edgeCountLabel.Name = "edgeCountLabel";
            this.edgeCountLabel.Size = new System.Drawing.Size(154, 20);
            this.edgeCountLabel.TabIndex = 27;
            this.edgeCountLabel.Text = "Connection Count: 0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(830, 604);
            this.Controls.Add(this.edgeCountLabel);
            this.Controls.Add(this.nodeCountLabel);
            this.Controls.Add(this.rmvToLabel);
            this.Controls.Add(this.rmvFromLabel);
            this.Controls.Add(this.removeLabel);
            this.Controls.Add(this.removeButton);
            this.Controls.Add(this.rmvToTextBox);
            this.Controls.Add(this.rmvFromTextBox);
            this.Controls.Add(this.displayMothersLabel);
            this.Controls.Add(this.allReachableLabel);
            this.Controls.Add(this.displayAirportsLabel);
            this.Controls.Add(this.checkConnectionsButton);
            this.Controls.Add(this.checkCodeLabel);
            this.Controls.Add(this.connectionCheckLabel);
            this.Controls.Add(this.connectionCheckTextBox);
            this.Controls.Add(this.toLabel);
            this.Controls.Add(this.fromLabel);
            this.Controls.Add(this.addConnectionLabel);
            this.Controls.Add(this.addConnectionButton);
            this.Controls.Add(this.toTextBox);
            this.Controls.Add(this.fromTextBox);
            this.Controls.Add(this.airportCodeLabel);
            this.Controls.Add(this.airportCodeTextBox);
            this.Controls.Add(this.addAirportButton);
            this.Controls.Add(this.addAirportLabel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label addAirportLabel;
        private System.Windows.Forms.Button addAirportButton;
        private System.Windows.Forms.TextBox airportCodeTextBox;
        private System.Windows.Forms.Label airportCodeLabel;
        private System.Windows.Forms.TextBox fromTextBox;
        private System.Windows.Forms.TextBox toTextBox;
        private System.Windows.Forms.Button addConnectionButton;
        private System.Windows.Forms.Label addConnectionLabel;
        private System.Windows.Forms.Label fromLabel;
        private System.Windows.Forms.Label toLabel;
        private System.Windows.Forms.TextBox connectionCheckTextBox;
        private System.Windows.Forms.Label connectionCheckLabel;
        private System.Windows.Forms.Label checkCodeLabel;
        private System.Windows.Forms.Button checkConnectionsButton;
        private System.Windows.Forms.Label displayAirportsLabel;
        private System.Windows.Forms.Label allReachableLabel;
        private System.Windows.Forms.Label displayMothersLabel;
        private System.Windows.Forms.Label rmvToLabel;
        private System.Windows.Forms.Label rmvFromLabel;
        private System.Windows.Forms.Label removeLabel;
        private System.Windows.Forms.Button removeButton;
        private System.Windows.Forms.TextBox rmvToTextBox;
        private System.Windows.Forms.TextBox rmvFromTextBox;
        private System.Windows.Forms.Label nodeCountLabel;
        private System.Windows.Forms.Label edgeCountLabel;
    }
}

