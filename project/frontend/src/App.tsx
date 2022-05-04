import React from 'react';
import Footer from './components/Footer';
import GreetingText from './components/GreetingText';
import Header from './components/Header';
import DataCat from './components/ShowEventCategories';

/**
 * Base of application.
 * @returns the application with all its components
 */
export default function App() {
  return (
    <>
      <Header />
      <GreetingText />
      <DataCat />
      <Footer />
    </>
  );
}