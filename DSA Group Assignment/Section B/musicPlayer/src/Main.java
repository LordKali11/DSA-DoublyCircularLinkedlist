// Don't edit the code
import java.util.*;

class Main {

    static Node begin;
    
    static class Node {

        String data;
        Node next;
        Node previous;
    }


    static Node addSong(String song)

    {
        
        if (begin == null) {
            Node new_node = new Node();
            new_node.data = song;
            new_node.next = new_node.previous = new_node;
            begin = new_node;
            return begin;
        }

        
        Node last = (begin).previous;
        Node new_node = new Node();
        new_node.data = song;
        
        new_node.next = begin;
        (begin).previous = new_node;
        
        new_node.previous = last;
        last.next = new_node;
        return begin;
    }
    static Node removeSong(Node begin, String song)
    {
        
        if (begin == null)
            return null;

        
        Node current = begin, previous_node = null;
        while (!current.data.equals( song)) {
            if (current.next == begin) {
                System.out.print("\nPlaylist doesn't have song with name: " + song);
                return begin;
            }

            previous_node = current;
            current = current.next;
        }

        if (current.next == begin && previous_node == null) {
            (begin) = null;
            return begin;
        }


        if (current == begin) {

            previous_node = (begin).previous;


            begin = (begin).next;

            previous_node.next = begin;
            (begin).previous = previous_node;
        }

        // check if it is the last node
        else if (current.next == begin) {
            previous_node.next = begin;
            (begin).previous = previous_node;
        }
        else {

            Node temp = current.next;
            previous_node.next = temp;
            temp.previous = previous_node;
        }
        return begin;
    }
    static String searchSong(Node begin, String song)
    {

        Node temp = begin;


        int count = 0, found = 0;


        if(temp == null)
            return song;
        else
        {
            while(!temp.next.equals(begin))
            {

                count++;


                if(temp.data.equals( song))
                {
                    found = 1;
                    count--;
                    break;
                }


                temp = temp.next;
            }


            if(temp.data.equals(song))
            {
                count++;
                found = 1;
            }

            
            if(found == 1)
                System.out.println("\n"+song +" found at position "+
                        count);
            else
                System.out.println("\n"+song +" not found");
        }
        return song;
    }



    static void play(Node begin)
    {
        Node temp = begin;
     


        System.out.print(
                "\n!Playing playlist! \n");
        while (!temp.next.equals(begin)) {
            System.out.println("playing " + temp.data);
            temp = temp.next;

        }
        System.out.print("playing " + temp.data);
        
        System.out.print(
            "Playing playlist in reverse direction \n");
        Node last = begin.previous;
        temp = last;
        while (temp.previous != last) {
            System.out.print("playing" +  temp.data);
            temp = temp.previous;
        }
        System.out.print("playing" + temp.data);
        }
    static void repeat(Node begin, int count){
        Node temp = begin;
        //boolean running = true;
        System.out.println(
                "\n!Playing playlist! \n");
        for(int i = 0; i < count; i++){
            System.out.println("playing " + temp.data);
            temp = temp.next;}
    }

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        Node begin = null;

        int choice ;
        String song_name;
        int num_of_song = 0;

        boolean running = true;
        while(running){
            System.out.println("Enter Choice \n1. Add Song to playlist \n2. Remove Song from playlist \n3. Search Song in playlist \n4. play playlist \n5. repeat playlist \n6. Quit");

            choice = input.nextInt();
            if (choice == 1){
                System.out.println("Enter song name to be added to playlist: ");
                song_name = input.next();
                num_of_song += 1;
                begin = addSong(song_name);
            }
            else if (choice == 2){
                System.out.println("enter song name to be removed from playlist:");
                song_name = input.next();
                begin = removeSong(begin,song_name);
            }
            else if (choice == 3){
                System.out.println("Enter song name to be searched in the playlist");
                song_name = input.next();
                searchSong(begin,song_name);
            }
            else if (choice == 4){
                play(begin);
            }
            else if (choice == 5){
                System.out.println("Enter the amount of times that the playlist must repeat itself");
                int count = input.nextInt();
                count = num_of_song * count;
                repeat(begin, count);
            }
            else if (choice == 6) {
                System.out.println("You have quited");
                running = false;
            }

        }
    }
}

