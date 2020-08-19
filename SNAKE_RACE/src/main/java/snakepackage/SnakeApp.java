package snakepackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import enums.GridSize;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author jd-
 *
 */
public class SnakeApp extends JFrame {
	private JButton inicio;
	private JButton reanudar;
	private JButton pausar;
	private boolean runnig;
    private static SnakeApp app;
    public static final int MAX_THREADS = 8;
    Snake[] snakes = new Snake[MAX_THREADS];
    private static final Cell[] spawn = {
        new Cell(1, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2,
        3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, GridSize.GRID_HEIGHT - 2),
        new Cell(1, 3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2,
        GridSize.GRID_HEIGHT - 2)};
    private JFrame frame;
    private static Board board;
    int nr_selected = 0;
    Thread[] thread = new Thread[MAX_THREADS];

    public SnakeApp() {
    	runnig = false;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("The Snake Race");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(618, 640);
        frame.setSize(GridSize.GRID_WIDTH * GridSize.WIDTH_BOX + 17,
                GridSize.GRID_HEIGHT * GridSize.HEIGH_BOX + 40);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2,
                dimension.height / 2 - frame.getHeight() / 2);
        board = new Board();
        
        
        frame.add(board,BorderLayout.CENTER);
        inicio = new JButton("Iniciar");
        pausar = new JButton("pausar");
        reanudar = new JButton("reanudar");
        JPanel actionsBPabel=new JPanel();
        actionsBPabel.setLayout(new FlowLayout());
        actionsBPabel.add(inicio);
        actionsBPabel.add(pausar);
        actionsBPabel.add(reanudar);
        frame.add(actionsBPabel,BorderLayout.SOUTH);
        actions();


    }

    public static void main(String[] args) {
        app = new SnakeApp();
        app.init();
    }

    private void init() {
        
        
        
        for (int i = 0; i != MAX_THREADS; i++) {
            
            snakes[i] = new Snake(i + 1, spawn[i], i + 1);
            snakes[i].addObserver(board);
            thread[i] = new Thread(snakes[i]);
            //thread[i].start();
        }

        frame.setVisible(true);

            
        while (true) {
            int x = 0;
            for (int i = 0; i != MAX_THREADS; i++) {
                if (snakes[i].isSnakeEnd() == true) {
                    x++;
                }
            }
            if (x == MAX_THREADS) {
                break;
            }
        }


        System.out.println("Thread (snake) status:");
        for (int i = 0; i != MAX_THREADS; i++) {
            System.out.println("["+i+"] :"+thread[i].getState());
        }
        

    }

    public static SnakeApp getApp() {
        return app;
    }

    public void actions() {
        ActionListener oyenteBotonInicio = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                iniciar();
            }   
        };  
        inicio.addActionListener(oyenteBotonInicio);
        
        ActionListener oyenteBotonPausar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pausar();
            }   
        };  
        pausar.addActionListener(oyenteBotonPausar);
        
        ActionListener oyenteBotonReanudar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reanudar();
            }   
        };  
        reanudar.addActionListener(oyenteBotonReanudar);
        
        
    }
    
    public void iniciar() {
    	
    	if (runnig) {
    		System.out.println("Corriendo");
    	} else {
            for (int i = 0; i != MAX_THREADS; i++) {

                thread[i].start();
            }
            
            iniciado();
    	}
    }
    
    public void pausar() {
    	int snake = 0;
    	int max = 0;
        for (int i = 0; i != MAX_THREADS; i++) {

            snakes[i].pause();
        }
        
        for (int i = 0; i < snakes.length; i++) {
        	if(snakes[i].getBody().size() > max) {
        		max = snakes[i].getBody().size();
        		snake = i;
        	}
        }
        
        System.out.println("la serpiente mas larga es la " + snake);
        
        
        
        
    }
    
    public void reanudar() {

        for (int i = 0; i != MAX_THREADS; i++) {

           snakes[i].continuar();
        }
    } 
    
    public void iniciado() {
    	runnig = true;
    }
    
    
}
