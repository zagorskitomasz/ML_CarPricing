# ML_CarPricing

<b>Car pricing algorithm:</b>
1. Take car info from user (type, age, engine parameters etc.)
2. Download data from public car store website for specified car type.
3. Use downloaded data to train Theta parameters for linear regression function.
4. Predict car price with h(x) where h is trained linear regression function and x is user's car parameters vector.
5. Return result to user.

<b>Technologies:</b>
- Java 8,
- Spring (Boot and DI container),
- Vaadin frontend,
- Some ML/math library,

<b>==TODO==</b>

A. PROJECT SETUP
1. <s>System architecture diagram.</s>
2. <s>Git repo.</s>
3. Spring Boot app.
4. Choose and add ML/math library.
5. Choose website with car prices data.

B. IMPLEMENTATION
1. Controller.
2. Data service
  a) connector,
  b) parser.
3. Data model.
4. Processing service
  a) algorithm trainer,
  b) algorithm user.
5. Frontend

(https://github.com/zagorskitomasz/ML_CarPricing/blob/master/Car-pricing-diagram.jpg)
