import React from 'react';
import Footer from './Components/footer';
import GreetingText from './Components/greetingText';
import Header from './Components/header';
import JsonDataDisplay from './Components/showEvents';

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

