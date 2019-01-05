package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.VentaDemoDao;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "ventasController")
@SessionScoped
public class VentasController implements Serializable {
    
    //Variable del tipo grafico de lineas
    private LineChartModel salesChartModel;
    private LineChartModel salesChartModelDB;
    
    //Variable de tipo Grafico de Pie
    private PieChartModel  salesPieChartModel;
    
    private VentaDemoDao ventaDemoDao = new VentaDemoDao();
    
    //Variables del tipo Grafico de Barras
    private BarChartModel salesBarModel;
    
    //Variable que almacena el nombre del tipo de auto seleccionado
    //en un grafico interactivo
    private String selectPieChartAutoType;
            
    
    
    public VentasController() {
    }
    
    //Metodo inicializador
    //Este metodo se ejecuta inmediatamente despues de que la Clase
    //'ventasController' es construida
    @PostConstruct
    public void init(){
        //se invoca a metodo que configurara cada uno de los componentes del grafico
        createSalesModel();
        createSalesModelDB();
        createSalesPctModel();
        
        createSalesBarChartModel();
    }
    
    
    //Metodo que configura los elementos del Grafico
    public void createSalesModel(){
        //instanciamos nuestro objeto Grafico de Lineas
        salesChartModel = new LineChartModel();
        
        //Creamos una Serie para los Sedanes
        LineChartSeries sedans = new LineChartSeries();
        sedans.setFill(true); //indicamos que la serie tenga relleno
        sedans.setLabel("Sedans");  //titulo de la serie
        //Establecemos los datos de la serie
        sedans.set("Sedan - mini", 12000);
        sedans.set("Sedan - standard", 13500);
        sedans.set("Sedan - Custom", 15995);
        sedans.set("Sedan - Luxury", 18995);
        
        //Creamos una serie para los camiones
        LineChartSeries trucks = new LineChartSeries();
        trucks.setFill(true);
        trucks.setLabel("Trucks");
        trucks.set("pickUp", 17995);
        trucks.set("4x4", 22995);
        
        //Agregamos las Series al objeto 'salesChartModel'
        salesChartModel.addSeries(sedans);
        salesChartModel.addSeries(trucks);
        
        salesChartModel.setTitle("Grafico de Ventas");
        salesChartModel.setLegendPosition("ne");
        salesChartModel.setStacked(true);  //Para que una grafica se supoerponga a la otra
        salesChartModel.setShowPointLabels(true); //para que se muestren valores en el grafico
        salesChartModel.setAnimate(true); //para que el dibujo del grafico sea animado
        
        //Creamos y agregamos los objetos leyenda al objeto 'salesChartModel'
        Axis xAxis = new CategoryAxis("Cars");
        salesChartModel.getAxes().put(AxisType.X, xAxis);
        
        Axis yAxis = salesChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Sales");
        yAxis.setMin(0);
        yAxis.setMax(50000);
        
        
    }
    
    //Metodo que configura los elementos del Grafico
    public void createSalesModelDB() {
        //instanciamos nuestro objeto Grafico de Lineas
        salesChartModelDB = new LineChartModel();

        try {
            Map<Object, Number> mapVentas = new LinkedHashMap<>();

            //Recuperamos una Coleccion Map con lo sedanes
            mapVentas = ventaDemoDao.getVentasDemo('f');
            //Creamos una Serie para los Sedanes
            LineChartSeries sedans = new LineChartSeries();
            sedans.setFill(true); //indicamos que la serie tenga relleno
            sedans.setLabel("Sedans"); //titulo de la serie
            sedans.setData(mapVentas);
            
            mapVentas = ventaDemoDao.getVentasDemo('t');
            //Creamos una serie para los camiones
            LineChartSeries trucks = new LineChartSeries();
            trucks.setFill(true);
            trucks.setLabel("Trucks");
            trucks.setData(mapVentas);

            //Agregamos las Series al objeto 'salesChartModel'
            salesChartModelDB.addSeries(sedans);
            salesChartModelDB.addSeries(trucks);

            salesChartModelDB.setTitle("Grafico de Ventas");
            salesChartModelDB.setLegendPosition("ne");
            salesChartModelDB.setStacked(true);  //Para que una grafica se supoerponga a la otra
            salesChartModelDB.setShowPointLabels(true); //para que se muestren valores en el grafico
            salesChartModelDB.setAnimate(true); //para que el dibujo del grafico sea animado

            //Creamos y agregamos los objetos leyenda al objeto 'salesChartModel'
            Axis xAxis = new CategoryAxis("Cars");
            salesChartModelDB.getAxes().put(AxisType.X, xAxis);

            Axis yAxis = salesChartModelDB.getAxis(AxisType.Y);
            yAxis.setLabel("Sales");
            yAxis.setMin(0);
            yAxis.setMax(50000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //Modelo para crear Grafico de Barras
    private void createSalesBarChartModel(){
        //instanciamos el objeto BarChartModel
        salesBarModel = new BarChartModel();
        
        //Creamos Serie
        ChartSeries sedans = new ChartSeries();
        sedans.setLabel("Sedans");
        //Agregamos elementos a la serie
        sedans.set("2010", 1500000);
        sedans.set("2011", 1200000);
        sedans.set("2012", 1800000);
        sedans.set("2013", 1450000);
        sedans.set("2014", 1350000);
        
        //Creamos una segunda Serie
        ChartSeries trucks = new ChartSeries();
        trucks.setLabel("Trucks");
        //Agregamos elementos a la Serie
        trucks.set("2010", 1600000);
        trucks.set("2011", 1300000);
        trucks.set("2012", 1900000);
        trucks.set("2013", 1550000);
        trucks.set("2014", 1450000);
        
        //Creamos una tercera serie
        ChartSeries suvs = new ChartSeries();
        suvs.setLabel("SUVs");
        //Agregamos elementos a la serie
        suvs.set("2010", 2050000);
        suvs.set("2011", 2000000);
        suvs.set("2012", 1850000);
        suvs.set("2013", 1900000);
        suvs.set("2014", 1800000);
        
        //Configuramos el modelo
        salesBarModel.setAnimate(true);   //hace que el grafico sea animado
        //Agregamos series al modelo
        salesBarModel.addSeries(sedans);
        salesBarModel.addSeries(trucks);
        salesBarModel.addSeries(suvs);
        
        salesBarModel.setTitle("Ventas Automoviles");
        salesBarModel.setLegendPosition("ne");
        salesBarModel.setSeriesColors("0066ff, ffffff, ff0000"); //Especificamos colores para cada una de las series
        
        
        //Creamos leyendas de los ejes
        Axis xAxis =salesBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Tipo deAuto");
        
        Axis yAxis =salesBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Ventas");
        yAxis.setMin(0);
        yAxis.setMax(3000000);
        
        
    }
    
    
    public void createSalesPctModel(){
        salesPieChartModel = new PieChartModel();
        salesPieChartModel.setTitle("Tipos de autos en venta %");
        salesPieChartModel.setLegendPosition("e");
        salesPieChartModel.setShowDataLabels(true);
        salesPieChartModel.setDiameter(200); //establecemos el diametro
        
        //Seteamos los datos (Clave - valor)
        salesPieChartModel.set("Sedan", 375);
        salesPieChartModel.set("Truck", 284);
        salesPieChartModel.set("SUV", 215);
                
    }
    
    
    //Metodo que recibe el evento de seleccion de un tipo de auto
    //en un grafico interactivo
    public void itemSelect(ItemSelectEvent event){
        //obtenemos el valor entero que identifica al Item del grafico
        //que fue seleccionado
        int itemIndex = event.getItemIndex();
        //Damos a la variable Cadena el Nombre del tipo de auto selecciondado
        //en base a el valor de su indice
        switch(itemIndex){
            case 0:
                 this.selectPieChartAutoType = "Sedan";
                 //Aqui podemos ubicar mas informacion que queramos mostrar
            break;
            case 1:
                  this.selectPieChartAutoType = "Truck";
            break;    
            case 2:
                this.selectPieChartAutoType ="SUV";
            break;    
        }
        System.out.println("Estas Aqui");
        //Creamos un mensaje y lanzamos ese mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Informacion de Seleccion: ", "Tipo de auto seleccionado -->"+this.getSelectPieChartAutoType());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    //Getters y Setters

    public LineChartModel getSalesChartModel() {
        return salesChartModel;
    }

    public void setSalesChartModel(LineChartModel salesChartModel) {
        this.salesChartModel = salesChartModel;
    }

    public LineChartModel getSalesChartModelDB() {
        return salesChartModelDB;
    }

    public void setSalesChartModelDB(LineChartModel salesChartModelDB) {
        this.salesChartModelDB = salesChartModelDB;
    }

    public BarChartModel getSalesBarModel() {
        return salesBarModel;
    }

    public void setSalesBarModel(BarChartModel salesBarModel) {
        this.salesBarModel = salesBarModel;
    }

    public PieChartModel getSalesPieChartModel() {
        return salesPieChartModel;
    }

    public void setSalesPieChartModel(PieChartModel salesPieChartModel) {
        this.salesPieChartModel = salesPieChartModel;
    }

    public String getSelectPieChartAutoType() {
        return selectPieChartAutoType;
    }

    public void setSelectPieChartAutoType(String selectPieChartAutoType) {
        this.selectPieChartAutoType = selectPieChartAutoType;
    }
    
}
