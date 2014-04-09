
#include <graphite2/Segment.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct cluster_t {
    unsigned int base_char;
    unsigned int num_chars;
    unsigned int base_glyph;
    unsigned int num_glyphs;
} cluster_t;

/* usage: ./cluster fontfile.ttf string */
int main(int argc, char **argv)
{
    int rtl = 0;                /* are we rendering right to left? probably not */
    int pointsize = 12;         /* point size in points */
    int dpi = 96;               /* work with this many dots per inch */

    char *pError;               /* location of faulty utf-8 */
    gr_font *font = NULL;
    size_t numCodePoints = 0;
    gr_segment * seg = NULL;
    cluster_t *clusters;
    int ic, ci = 0;
    const gr_slot *s, *is;
    FILE *log;
    gr_face *face = gr_make_file_face(argv[1], 0);
    if (!face) return 1;
    font = gr_make_font(pointsize * dpi / 72.0f, face);
    if (!font) return 2;
    numCodePoints = gr_count_unicode_characters(gr_utf8, argv[2], NULL,
                (const void **)(&pError));
    if (pError || !numCodePoints) return 3;
    seg = gr_make_seg(font, face, 0, 0, gr_utf8, argv[2], numCodePoints, rtl);      /*<1>*/
    if (!seg) return 3;

    clusters = (cluster_t *)malloc(numCodePoints * sizeof(cluster_t));
    memset(clusters, 0, numCodePoints * sizeof(cluster_t));
    for (is = gr_seg_first_slot(seg), ic = 0; is; is = gr_slot_next_in_segment(is), ic++)
    {
        unsigned int before = gr_slot_before(is);
        unsigned int after = gr_slot_after(is);
        while (clusters[ci].base_char > before && ci)                               /*<2>*/
        {
            clusters[ci-1].num_chars += clusters[ci].num_chars;
            clusters[ci-1].num_glyphs += clusters[ci].num_glyphs;
            --ci;
        }

        if (gr_slot_can_insert_before(is) && clusters[ci].num_chars
                && before >= clusters[ci].base_char + clusters[ci].num_chars)       /*<3>*/
        {
            cluster_t *c = clusters + ci + 1;
            c->base_char = clusters[ci].base_char + clusters[ci].num_chars;
            c->num_chars = before - c->base_char;
            c->base_glyph = ic;
            c->num_glyphs = 0;
            ++ci;
        }
        ++clusters[ci].num_glyphs;

        if (clusters[ci].base_char + clusters[ci].num_chars < after + 1)            /*<4>*/
            clusters[ci].num_chars = after + 1 - clusters[ci].base_char;
    }

    ci = 0;
    log = fopen("cluster.log", "w");
    for (s = gr_seg_first_slot(seg); s; s = gr_slot_next_in_segment(s))
    {
        fprintf(log, "%d(%f,%f) ", gr_slot_gid(s), gr_slot_origin_X(s),
                                   gr_slot_origin_Y(s));
        if (--clusters[ci].num_glyphs == 0)                                         /*<5>*/
        {
            fprintf(log, "\n");
            ++ci;
        }
    }
    fclose(log);
    free(clusters);
    gr_seg_destroy(seg);
    gr_font_destroy(font);
    gr_face_destroy(face);
    return 0;
}
