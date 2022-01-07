import java.util.ArrayList;

// The Deck class represents a shuffled deck of cards.
// It provides several operations including
// initialize, shuffle, deal, and check if empty.
public class Deck {
    // cards is an ArrayList of all the Card objects in our deck
    private final ArrayList<Card> cardsList;

    // size is the number of not-yet-dealt cards.
    // Cards are dealt from the top (highest index) down.
    // The next card to be dealt is at size - 1.
    private int size;

    // Creates a new Deck instance given arrays of ranks, suits, and values
    // You will need to initialize both the cardsList and size instance variables
    // You should go through and make all possible pairs of suits and ranks
    public Deck(String[] ranks, String[] suits, int[] values) {
        // YOUR CODE HERE
        // Remember, in a constructor you need to first create the ArrayList for the instance variable!

        cardsList = new ArrayList<>();

		for (String suit : suits) {
			for (int j = 0; j < ranks.length; j++) {
				cardsList.add(new Card(ranks[j], suit, values[j]));
			}
		}

        this.size = cardsList.size();
    }

    // Deals a card from this deck.
    // return the card just dealt, or null if all the cards have been dealt already
    // Recall that the cards are dealt from top (highest-index) down
    // Updates the size as well
    public Card deal() {
        // delete and return the top card, if any
        if (this.size != 0) {
            Card card = this.cardsList.get(this.size - 1);
            this.size--;
            return card;
        }
        return null;
    }

    // Determines if this deck is empty (there are no undealt cards).
    // returns true if this deck is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the size (number of undealt cards) in this deck.
    public int getSize() {
        return this.size;
    }

    // Shuffles the deck by repeatedly randomly swapping pairs of cards
    // This method should change the order of the cards in cardsList
    // Shuffling should also reset the size variable to its original value
    public void shuffle() {
        // YOUR CODE HERE
        for (int i = 0; i < cardsList.size(); i++) {
            int randomIndex = (int) (Math.random() * cardsList.size());
            Card temp = cardsList.get(i);
            cardsList.set(i, cardsList.get(randomIndex));
            cardsList.set(randomIndex, temp);
        }
        size = cardsList.size();
    }

    // OPTIONAL: Write code that carries out a "perfect" shuffle
    // that perfectly interweaves the two halves of the deck

    // Before shuffling: [1, 2, 3, 4, 5, 21, 22, 23, 24, 25]
    // After shuffling: [1, 21, 2, 22, 3, 23, 4, 24, 5, 25]
    public void perfectShuffle() {
        int mid = (cardsList.size() / 2) - 1;
        for (int i = 2; i < mid + 2; i++) {
			cardsList.add(mid + i, cardsList.get(1));
			cardsList.remove(1);
        }
    }


    // CODE BELOW HERE IS ALREADY WRITTEN:

    /**
     * Generates and returns a string representation of this deck.
     *
     * @return a string representation of this deck.
     */
    @Override
    public String toString() {
        if (cardsList == null) {
            return "";
        }

        String rtn = "size = " + size + "\nUndealt cards: \n";

        for (int k = size - 1; k >= 0; k--) {
            rtn = rtn + cardsList.get(k);
            if (k != 0) {
                rtn = rtn + ", ";
            }
            if ((size - k) % 2 == 0) {
                // Insert carriage returns so entire deck is visible on console.
                rtn = rtn + "\n";
            }
        }

        rtn = rtn + "\nDealt cards: \n";
        for (int k = cardsList.size() - 1; k >= size; k--) {
            rtn = rtn + cardsList.get(k);
            if (k != size) {
                rtn = rtn + ", ";
            }
            if ((k - cardsList.size()) % 2 == 0) {
                // Insert carriage returns so entire deck is visible on console.
                rtn = rtn + "\n";
            }
        }

        rtn = rtn + "\n";
        return rtn;
    }

    // Returns the card at the given index of the deck
    // This method should only be used in testing code
    public Card getCardAtIndex(int idx) {
        return cardsList.get(idx);
    }

    // This version of equals() is used in the testing code
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            return cardsList.equals(((Deck) o).cardsList);
        }
    }
}
