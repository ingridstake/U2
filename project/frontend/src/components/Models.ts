/**
 * A type describing an event.
 */
export type event = {
    name: string;
    imageUrl: string;
    date: string;
    city: string;
}

/**
 * A type describing a category.
 */
export type category = {
    category: string;
    events: event[];
  }