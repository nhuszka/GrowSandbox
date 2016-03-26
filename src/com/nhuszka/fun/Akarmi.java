package com.nhuszka.fun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.j3d.Node;
import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.renderers.Renderer.Edge;

public class Akarmi {
	
	public static void main(String[] args) {
		DirectedSparseGraph g = new DirectedSparseGraph();
		g.addVertex("Vertex1");
		g.addVertex("Vertex2");
		g.addVertex("Vertex3");
		g.addEdge("Edge1", "Vertex1", "Vertex2");
		g.addEdge("Edge2", "Vertex1", "Vertex3");
		g.addEdge("Edge3", "Vertex3", "Vertex1");

		VisualizationImageServer vis = 
				new VisualizationImageServer(new CircleLayout(g), new Dimension(300, 300));

		vis.setBackground(Color.WHITE);
		vis.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		vis.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<Node, Edge>());
		vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Node>());
		vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

		vis.getImage(new Point2D.Double(300, 300), new Dimension(300, 300));
		BufferedImage image = (BufferedImage) vis.getImage(new Point2D.Double(300,300), new Dimension(300, 300));

		// Write image to a png file
		File outputfile = new File("D:\\graph.png");

		try {
		    ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
		    // Exception handling
		}
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(vis);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
