package dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {

    private Integer postId;

    private Integer id;

    private String name;

    private String email;

    private String body;

}
