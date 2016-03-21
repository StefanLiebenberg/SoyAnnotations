package example.models;

import org.slieb.soy.annotations.Soy;

import java.util.List;

@Soy
public class AnnotatedCommentExample {

    public AnnotatedCommentExample(Integer id, AnnotatedUserExample user, String content, List<AnnotatedCommentExample> comments) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.comments = comments;
    }

    @Soy.Field("Id")
    public Integer id;

    @Soy.Field("User")
    public AnnotatedUserExample user;

    @Soy.Field("Content")
    public String content;

    @Soy.Field("Comments")
    public List<AnnotatedCommentExample> comments;

}
