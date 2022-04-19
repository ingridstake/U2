import React from 'react';
import Footer from './Components/footer';
import GreetingText from './Components/greeting-text';
import Header from './Components/header';
import JsonDataDisplay from './Components/show_event';

export default function App() {
  return (
    <>
      <Header />
      <GreetingText />
      <JsonDataDisplay />
      <Footer />
    </>
  );
}

