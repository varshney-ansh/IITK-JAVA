import java.util.*;
import java.sql.*;
class A
{
	static String DB_URL = "jdbc:mysql://localhost:3306/ims";
	static String USER = "root";
	static String PASS = "";
	static Connection conn;
	static Scanner sc = new Scanner(System.in);

	public static void main(String...k)	
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		int choice;
		do
		{
		System.out.println("\n--Inventory Management System--");
		System.out.println("1. Add New Product");
		System.out.println("2. View All Products");
		System.out.println("3. Update Product Quantity");
		System.out.println("4. Insert Sale");
		System.out.println("5. View Sale Details");
		System.out.println("6. View Stock");
		System.out.println("7. View Profit/Revenue Details");
		System.out.println("8. Exit");
		System.out.print("Enter Your Choice: ");
		choice = sc.nextInt();
		sc.nextLine();

		switch(choice)
		{
			case 1: addProduct();
			break;
			case 2: viewProducts();
			break;
			case 3: updateQuantity();
			break;
			case 4: insertSale();
			break;
			case 5: viewSales();
			break;
			case 6: viewStock();
			break;
			case 7: viewProfit();
			break;
			case 8:
			System.out.println("Exit...");
			break;
			default:
			System.out.println("Invalid Choice");
			
			}
		}while(choice!=8);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}// close main method

    static void viewSales() throws Exception
    {
        String sql = "select * from sale";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (!rs.isBeforeFirst()) {
            System.out.println("No sales data available.");
            return;
        }
        while (rs.next()) {
            System.out.println("Sale ID: " + rs.getInt("saleId") + ", Product ID: " + rs.getInt("productId") +
                               ", Price: " + rs.getDouble("price") + ", Sale Quantity: " + rs.getInt("saleQty") +
                               ", Date: " + rs.getString("date"));
        }
    }

    static void viewProfit() throws Exception
    {
        int totalprofit = 0;
        Statement st = conn.createStatement();
        String sql = "select SaleQty,sum(price * saleQty) as totalSales from sale";
        ResultSet rs = st.executeQuery(sql);

        Statement stGetAllSalesProducts = conn.createStatement();
        String sqlGetAllSalesProducts = "select * from sale";
        ResultSet rsGetAllSalesProducts = stGetAllSalesProducts.executeQuery(sqlGetAllSalesProducts);
        while (rsGetAllSalesProducts.next()) {
            int productId = rsGetAllSalesProducts.getInt("productId");
            String sqlProduct = "select * from product where productid = ?";
            PreparedStatement psProduct = conn.prepareStatement(sqlProduct);
            psProduct.setInt(1, productId);
            ResultSet rsProduct = psProduct.executeQuery();
            if (rsProduct.next()) {
                int purchasePrice = rsProduct.getInt("purchaseprice");
                double profit = (rsGetAllSalesProducts.getDouble("price") - purchasePrice) * rsGetAllSalesProducts.getInt("saleQty");
                System.out.println("Profit for Product ID " + productId + ": " + profit);
                totalprofit += profit;
            }

        }

        System.out.println("Total Profit: " + totalprofit);

        if (rs.next()) {
            System.out.println("Total Revenue: " + rs.getDouble("totalSales"));
        }
        else {
            System.out.println("No sales data available.");
        }

    }

    static void insertSale()throws Exception
    {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        String sqlCheck = "select * from product where productid = ?";
        PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setInt(1, id);
        ResultSet rsCheck = psCheck.executeQuery();
        if (!rsCheck.next() || rsCheck.getInt("productqty") <= 0) {
            System.out.println("Product not available or out of stock.");
            return;
        }

        int productId = rsCheck.getInt("productid");
        int productSalePrice = rsCheck.getInt("saleprice");

        System.out.print("Enter Sale Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Sale Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        String sql = "insert into sale (productId, price, date, saleQty) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,productId);
        ps.setInt(2,productSalePrice);
        ps.setString(3,date);
        ps.setInt(4,qty);
        ps.executeUpdate();

        String updateSql = "update product set productqty = productqty - ? where productid = ?";
        PreparedStatement psUpdate = conn.prepareStatement(updateSql);
        psUpdate.setInt(1, qty);
        psUpdate.setInt(2, productId);
        psUpdate.executeUpdate();

        System.out.println("Sale Inserted Successfully!");
    }

	static void addProduct()throws Exception
	{
		System.out.print("Enter Product ID: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Product Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Purchase Price : ");
		int pp = sc.nextInt();
		System.out.print("Enter Sale Price : ");
		double sp = sc.nextDouble();
		System.out.print("Enter Quantity : ");
		int qty = sc.nextInt();

		String sql = "insert into product values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,id);
		ps.setString(2,name);
		ps.setInt(3,pp);
		ps.setDouble(4,sp);
		ps.setInt(5,qty);
		ps.executeUpdate();
		System.out.println("Product Add SuccessFully!");
	}

	static void viewStock()throws Exception
	{
		String sql = "select productid, productname, productqty from product";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
		}
	}

	static void viewProducts()throws Exception
	{
		String sql = "select *from product";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getDouble(4)+"  "+rs.getInt(5));
		}
	}

	static void updateQuantity()throws Exception
	{
		System.out.print("Enter Product Id : ");
		int id = sc.nextInt();
		System.out.print("Enter Quantity to add : ");
		int qty = sc.nextInt();
		
		String sql = "update product set productqty= productqty + ? where productid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,qty);
		ps.setInt(2,id);

		int r = ps.executeUpdate();
		if(r>0)
		{
			System.out.println("Quantity Updated Successfully.");
		}
		else
		{
			System.out.println("Product Not Found.");
		}
		
		
	}
}