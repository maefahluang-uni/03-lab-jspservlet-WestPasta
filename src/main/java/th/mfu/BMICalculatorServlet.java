package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calbmi")
public class BMICalculatorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request: "weight" and "height"
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));

        // Calculate BMI
        double bmi = calculateBMI(weight, height);

        // Determine the built from BMI
        String builtType = determineBuiltType(bmi);

        // Round the BMI to an integer
        int roundedBMI = (int) Math.round(bmi);

        // Add BMI and built type to the request's attributes
        request.setAttribute("bmi", roundedBMI);
        request.setAttribute("builtType", builtType);

        // Forward to jsp
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
    }

    private double calculateBMI(double weight, double height) {
        // BMI formula: weight (kg) / (height (m) * height (m))
        return weight / (height * height);
    }
        private String determineBuiltType(double bmi) {
        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "normal";
        } else if (bmi < 30) {
            return "overweight";
        } else if (bmi < 35) {
            return "obese";
        } else {
            return "extremely obese";
        }
    }
}
