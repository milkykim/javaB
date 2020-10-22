package ch12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GraphicDrawlineTest extends JFrame {
	public GraphicDrawlineTest() {
		setTitle("���콺�� �� �׸��� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ ��� ���2: MyPanel ��ü ���� �� �� ��ü�� ������ Ŭ���� ��� ���
		// MyPanel mp = new MyPanel();
		// mp.addMouseListener(.......);
		setContentPane(new MyPanel());
		
		setSize(500,500);
		setVisible(true);
	}
	class Line{
		int x1,y1,x2,y2;
		public Line(int a, int b, int c, int d) {
			x1 = a; y1 = b; x2 = c; y2 = d;
		}
	}
	class MyPanel extends JPanel {
		private int x1, y1, x2, y2;
		
		// �������� �׸��� ���ؼ� vector�迭�� �����
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		private Vector<Line> vLine = new Vector<Line>();
		
		// ������ ��� ���1: MyPanel ������ �ȿ��� ����ϴ� ��
		public MyPanel() {
			MyAdapter ma = new MyAdapter();
			
			this.addMouseListener(ma);
			this.addMouseMotionListener(ma);
			
		}
		
		class MyAdapter extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ��ǥ�� ����
				x1 = e.getX();
				y1 = e.getY();
				vStart.add(e.getPoint());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// ���� ��ǥ�� ����
				x2 = e.getX();
				y2 = e.getY();
				
				vEnd.add(e.getPoint());
				vLine.add(new Line(x1,y1,x2,y2));
				
				// �̰� ������ �ƿ� �׷����� ����!!
				repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// ���� ��ǥ�� ����
				x2 = e.getX();
				y2 = e.getY();
				
				// �̰� ������ �ƿ� �׷����� ����!!
				repaint();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// �׸� �׸��� �۾�(�ʿ��� ��ǥ, ũ��, ����� ����)
			for(int i=0; i<vStart.size(); i++) {
				//Point s = vStart.elementAt(i);
				//Point e = vEnd.elementAt(i);
				//g.drawLine((int)s.getX(), (int)s.getY(), 
				//		(int)e.getX(), (int)e.getY());
				Line l = vLine.elementAt(i);
				g.drawLine(l.x1, l.y1, l.x2, l.y2);
			}
			
			g.drawLine(x1, y1, x2, y2);
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		
			int w = getWidth()/10;
			for(int i=1; i<=9; i++) {
				g.drawLine(i*w, 0, i*w, getHeight());
			}
			
			int h = getWidth()/10;
			for(int i=1; i<=9; i++) {
				g.drawLine(0, i*h, getWidth(), i*h);
			}
		
		}
		
	}

	public static void main(String[] args) {
		new GraphicDrawlineTest();
	}

}