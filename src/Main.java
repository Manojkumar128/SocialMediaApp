import services.*; 
import java.util.Scanner; 
public class Main { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        UserService userService = new UserService(); 
        PostService postService = new PostService(); 
        LikeService likeService = new LikeService(); 
        CommentService commentService = new CommentService(); 
        int loggedInUserId = -1; 
        System.out.println("=== Welcome to Mini Social Media App ==="); 
        while (true) { 
            if (loggedInUserId == -1) { 
                System.out.println("\n1. Register"); 
                System.out.println("2. Login"); 
                System.out.println("3. Exit"); 
                System.out.print("Choose option: "); 
                int choice = sc.nextInt(); sc.nextLine(); 
                switch (choice) { 
                    case 1: 
                        System.out.print("Enter username: "); 
                        String regUser = sc.nextLine(); 
                        System.out.print("Enter password: "); 
                        String regPass = sc.nextLine(); 
                        userService.registerUser(regUser, regPass); 
                        break; 
                    case 2: 
                        System.out.print("Enter username: "); 
                        String logUser = sc.nextLine(); 
                        System.out.print("Enter password: "); 
                        String logPass = sc.nextLine(); 
                        loggedInUserId = userService.loginUser(logUser, logPass); 
                        break; 
                    case 3: 
                        System.out.println("Goodbye!"); 
                        System.exit(0); 
                    default: 
                        System.out.println("Invalid option!"); 
                } 
            } else { 
                System.out.println("\n--- Menu ---"); 
                System.out.println("1. Create Post"); 
                System.out.println("2. View Posts"); 
                System.out.println("3. Like Post"); 
                System.out.println("4. Comment on Post"); 
                System.out.println("5. View Comments"); 
                System.out.println("6. Logout"); 
                System.out.print("Choose option: "); 
                int choice = sc.nextInt(); sc.nextLine(); 
                switch (choice) { 
                    case 1: 
                        System.out.print("Enter your post: "); 
                        String content = sc.nextLine(); 
                        postService.createPost(loggedInUserId, content); 
                        break; 
                    case 2: 
                        postService.viewPosts(); 
                        break; 
                    case 3: 
                        System.out.print("Enter Post ID: "); 
                        int postId = sc.nextInt(); 
                        likeService.likePost(loggedInUserId, postId); 
                        break; 
                    case 4: 
                        System.out.print("Enter Post ID: "); 
                        int cPostId = sc.nextInt(); sc.nextLine(); 
                        System.out.print("Enter comment: "); 
                        String comment = sc.nextLine(); 
                        commentService.addComment(loggedInUserId, cPostId, comment); 
                        break; 
                    case 5: 
                        System.out.print("Enter Post ID: "); 
                        int vPostId = sc.nextInt(); 
                        commentService.viewComments(vPostId); 
                        break; 
                    case 6: 
                        loggedInUserId = -1; 
                        System.out.println("Logged out!"); 
                        break; 
                    default: 
                        System.out.println("Invalid option!"); 
                } 
            } 
        } 
    } 
} 
