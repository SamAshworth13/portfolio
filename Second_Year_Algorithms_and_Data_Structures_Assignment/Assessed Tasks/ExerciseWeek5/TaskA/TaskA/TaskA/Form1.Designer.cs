namespace TaskA
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
            this.insertNodeButton = new System.Windows.Forms.Button();
            this.idTextBox = new System.Windows.Forms.TextBox();
            this.idLabel = new System.Windows.Forms.Label();
            this.fromLabel = new System.Windows.Forms.Label();
            this.toLabel = new System.Windows.Forms.Label();
            this.insertNodeLabel = new System.Windows.Forms.Label();
            this.fromTextBox = new System.Windows.Forms.TextBox();
            this.toTextBox = new System.Windows.Forms.TextBox();
            this.insertEdgeLabel = new System.Windows.Forms.Label();
            this.insertEdgeButton = new System.Windows.Forms.Button();
            this.nodeCountLabel = new System.Windows.Forms.Label();
            this.edgeCountLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // insertNodeButton
            // 
            this.insertNodeButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.insertNodeButton.Location = new System.Drawing.Point(345, 41);
            this.insertNodeButton.Name = "insertNodeButton";
            this.insertNodeButton.Size = new System.Drawing.Size(104, 40);
            this.insertNodeButton.TabIndex = 0;
            this.insertNodeButton.Text = "Insert Node";
            this.insertNodeButton.UseVisualStyleBackColor = true;
            this.insertNodeButton.Click += new System.EventHandler(this.insertNodeButton_Click);
            // 
            // idTextBox
            // 
            this.idTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.idTextBox.Location = new System.Drawing.Point(196, 48);
            this.idTextBox.MaxLength = 1;
            this.idTextBox.Name = "idTextBox";
            this.idTextBox.Size = new System.Drawing.Size(75, 26);
            this.idTextBox.TabIndex = 1;
            // 
            // idLabel
            // 
            this.idLabel.AutoSize = true;
            this.idLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.idLabel.Location = new System.Drawing.Point(12, 51);
            this.idLabel.Name = "idLabel";
            this.idLabel.Size = new System.Drawing.Size(72, 20);
            this.idLabel.TabIndex = 2;
            this.idLabel.Text = "Node ID:";
            // 
            // fromLabel
            // 
            this.fromLabel.AutoSize = true;
            this.fromLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.fromLabel.Location = new System.Drawing.Point(12, 157);
            this.fromLabel.Name = "fromLabel";
            this.fromLabel.Size = new System.Drawing.Size(145, 20);
            this.fromLabel.TabIndex = 3;
            this.fromLabel.Text = "From Node with ID:";
            // 
            // toLabel
            // 
            this.toLabel.AutoSize = true;
            this.toLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.toLabel.Location = new System.Drawing.Point(12, 214);
            this.toLabel.Name = "toLabel";
            this.toLabel.Size = new System.Drawing.Size(126, 20);
            this.toLabel.TabIndex = 4;
            this.toLabel.Text = "To Node with ID:";
            // 
            // insertNodeLabel
            // 
            this.insertNodeLabel.AutoSize = true;
            this.insertNodeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.insertNodeLabel.Location = new System.Drawing.Point(12, 9);
            this.insertNodeLabel.Name = "insertNodeLabel";
            this.insertNodeLabel.Size = new System.Drawing.Size(100, 20);
            this.insertNodeLabel.TabIndex = 5;
            this.insertNodeLabel.Text = "Insert Node: ";
            // 
            // fromTextBox
            // 
            this.fromTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.fromTextBox.Location = new System.Drawing.Point(196, 154);
            this.fromTextBox.MaxLength = 1;
            this.fromTextBox.Name = "fromTextBox";
            this.fromTextBox.Size = new System.Drawing.Size(75, 26);
            this.fromTextBox.TabIndex = 6;
            // 
            // toTextBox
            // 
            this.toTextBox.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.toTextBox.Location = new System.Drawing.Point(196, 211);
            this.toTextBox.MaxLength = 1;
            this.toTextBox.Name = "toTextBox";
            this.toTextBox.Size = new System.Drawing.Size(75, 26);
            this.toTextBox.TabIndex = 7;
            // 
            // insertEdgeLabel
            // 
            this.insertEdgeLabel.AutoSize = true;
            this.insertEdgeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.insertEdgeLabel.Location = new System.Drawing.Point(12, 106);
            this.insertEdgeLabel.Name = "insertEdgeLabel";
            this.insertEdgeLabel.Size = new System.Drawing.Size(96, 20);
            this.insertEdgeLabel.TabIndex = 8;
            this.insertEdgeLabel.Text = "Insert Edge:";
            // 
            // insertEdgeButton
            // 
            this.insertEdgeButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.insertEdgeButton.Location = new System.Drawing.Point(345, 204);
            this.insertEdgeButton.Name = "insertEdgeButton";
            this.insertEdgeButton.Size = new System.Drawing.Size(104, 40);
            this.insertEdgeButton.TabIndex = 9;
            this.insertEdgeButton.Text = "Insert Edge";
            this.insertEdgeButton.UseVisualStyleBackColor = true;
            this.insertEdgeButton.Click += new System.EventHandler(this.insertEdgeButton_Click);
            // 
            // nodeCountLabel
            // 
            this.nodeCountLabel.AutoSize = true;
            this.nodeCountLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nodeCountLabel.Location = new System.Drawing.Point(12, 304);
            this.nodeCountLabel.Name = "nodeCountLabel";
            this.nodeCountLabel.Size = new System.Drawing.Size(111, 20);
            this.nodeCountLabel.TabIndex = 12;
            this.nodeCountLabel.Text = "Node Count: 0";
            // 
            // edgeCountLabel
            // 
            this.edgeCountLabel.AutoSize = true;
            this.edgeCountLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.edgeCountLabel.Location = new System.Drawing.Point(12, 387);
            this.edgeCountLabel.Name = "edgeCountLabel";
            this.edgeCountLabel.Size = new System.Drawing.Size(111, 20);
            this.edgeCountLabel.TabIndex = 13;
            this.edgeCountLabel.Text = "Edge Count: 0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(461, 476);
            this.Controls.Add(this.edgeCountLabel);
            this.Controls.Add(this.nodeCountLabel);
            this.Controls.Add(this.insertEdgeButton);
            this.Controls.Add(this.insertEdgeLabel);
            this.Controls.Add(this.toTextBox);
            this.Controls.Add(this.fromTextBox);
            this.Controls.Add(this.insertNodeLabel);
            this.Controls.Add(this.toLabel);
            this.Controls.Add(this.fromLabel);
            this.Controls.Add(this.idLabel);
            this.Controls.Add(this.idTextBox);
            this.Controls.Add(this.insertNodeButton);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button insertNodeButton;
        private System.Windows.Forms.TextBox idTextBox;
        private System.Windows.Forms.Label idLabel;
        private System.Windows.Forms.Label fromLabel;
        private System.Windows.Forms.Label toLabel;
        private System.Windows.Forms.Label insertNodeLabel;
        private System.Windows.Forms.TextBox fromTextBox;
        private System.Windows.Forms.TextBox toTextBox;
        private System.Windows.Forms.Label insertEdgeLabel;
        private System.Windows.Forms.Button insertEdgeButton;
        private System.Windows.Forms.Label nodeCountLabel;
        private System.Windows.Forms.Label edgeCountLabel;
    }
}

