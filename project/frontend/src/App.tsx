import React from 'react';
import Footer from './components/Footer';
import GreetingText from './components/GreetingText';
import Header from './components/Header';
import DataCat from './components/ShowEventCategories';
import JsonDataDisplay from './components/ShowEvents';
import SearchBar from './components/SearchBar';

/**
 * Base of application.
 * @returns the application with all its components
 */
export default function App() {
  return (
    <>
      <Header />
      <GreetingText />
      <SearchBar />
      <DataCat />
      <Footer />
    </>
  );
}