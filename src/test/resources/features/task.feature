@regression
Feature: Case1

  Background: Open Page and Check Page
    Given Check page url contains "iyzico"

    Scenario: Confirm Order
      And Click "secondProduct" element
      Then Wait until "productText" element is visible
      Then Click "addToCart" element
      And Check "productAddedMessage" field existence on page
      Then Click "basketButton" element
      And Click "goToPaymentPage" element
      Then Wait until "paymentText" element is visible
      And Check page title contains "Ödeme"
      And Wait for given seconds 3
      And Click "selectPaymentWithCard" element
      And Click "payWithSelectedPayment" element
      And Fill "cardHolderName" field with "kredi karti"
      And Fill "cardNumber" field with "5890040000000016"
      And Fill "expiryDate" field with "12/25"
      And Fill "cvcNumber" field with "152"
      Then Click "payButton" element
      Then Check "smsCodeText" field existence on page
      And Get text from "smsCodeText" and paste into this field "smsCodeTextBox"
      And Click "submitButton" element
      Then Check "orderCompletedText" field existence on page



      

