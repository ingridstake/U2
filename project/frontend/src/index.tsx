import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import JsonDataDisplay from './Components/show_event';
import Header from './Components/header';
import Footer from './Components/footer';
import reportWebVitals from './reportWebVitals';
import Greeting_text from './Components/greeting-text'

const root = ReactDOM.createRoot(document.getElementById("root")!);
root.render(<>
  <Header />
  <Greeting_text />
  <JsonDataDisplay />
  <Footer />
</>);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
