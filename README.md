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
3. <s>Spring Boot app.</s>
4. <s>Choose and add ML/math library.</s>
5. <s>Choose website with car prices data.</s>

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

![diagram](https://github.com/zagorskitomasz/ML_CarPricing/blob/master/Car-pricing-diagram.jpg)

Allegro REST API usage algorithm:
1. Create dedicated Allegro user.
2. Manually get first OAuth token.
3. Insert token into DB.
4. App use: try authenticate with current token (success -> 5, failure -> 6).
5. Use token to communicate with API.
6. Automatically refresh token and save in DB. Go to 4.
