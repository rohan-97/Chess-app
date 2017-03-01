package com.example.rohan.chess;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class calculate {
    int r, c;

    public void findxy(int no) {
        if (no > 56) {
            r = 8;
            c = no - 56;
        } else if (no > 48) {
            r = 7;
            c = no - 48;
        } else if (no > 40) {
            r = 6;
            c = no - 40;
        } else if (no > 32) {
            r = 5;
            c = no - 32;
        } else if (no > 24) {
            r = 4;
            c = no - 24;
        } else if (no > 16) {
            r = 3;
            c = no - 16;
        } else if (no > 8) {
            r = 2;
            c = no - 8;
        } else if (no > 0) {
            r = 1;
            c = no;
        }
    }

}

public class MainActivity extends AppCompatActivity {
    TextView display;
    int turn;
    ImageView[] images = new ImageView[65];
    String selectedpiece = null;
    int selectedposn = 0;
    int kingendangered=-1 ;
    String color[]=new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  display = (TextView) findViewById(R.id.textView);
        for (int k = 1; k <= 64; k++) {
            int idnt = getResources().getIdentifier("i" + k, "id", getPackageName());
            images[k] = (ImageView) findViewById(idnt);
        }
        turn=1;
        color[0]="b";
        color[2]="w";
    }


    public void myfunc(View view) {
        try{
        String tag = view.getTag().toString();
        ImageView iv = (ImageView) findViewById(view.getId());
        ColorDrawable dra = (ColorDrawable) view.getBackground();
        calculate cal = new calculate();
        if (tag.length() == 4) {
            if ((tag.substring(2, 3).equals("w") && turn > 0) || (tag.substring(2, 3).equals("b") && turn < 0)) {
                if (tag.length() == 4 && (dra.getColor() == Color.parseColor("#ffffff") || dra.getColor() == Color.parseColor("#bebebe"))) {
                    int no = Integer.parseInt(tag.substring(0, 2));
                    int row, col;

                    cal.findxy(no);
                    row = cal.r;
                    col = cal.c;
                    String colour = tag.substring(2, 3);
                    String piece = tag.substring(3);
                    switch (piece) {
                        case "r": {
                            rook(row, col, colour);
                            break;
                        }
                        case "h": {
                            horse(row, col, colour);
                            break;
                        }
                        case "b": {
                            bishop(row, col, colour);
                            break;
                        }
                        case "q": {
                            queen(row, col, colour);
                            break;
                        }
                        case "k": {
                            king(row, col, colour);
                            break;
                        }
                        case "p": {
                            pawn(row, col, colour);
                            break;
                        }
                    }
                }
            }
        }
        if (dra.getColor() == Color.parseColor("#a1e067") || dra.getColor() == Color.parseColor("#80b352")) {
            String text = tag.substring(0, 2) + selectedpiece;
            assert iv != null;
            iv.setTag(text);
            switch (selectedpiece) {
                case "wr":
                    iv.setImageResource(R.drawable.wr);
                    break;
                case "wh":
                    iv.setImageResource(R.drawable.wn);
                    break;
                case "wb":
                    iv.setImageResource(R.drawable.wb);
                    break;
                case "wq":
                    iv.setImageResource(R.drawable.wq);
                    break;
                case "wk":
                    iv.setImageResource(R.drawable.wk);
                    break;
                case "wp":
                    iv.setImageResource(R.drawable.wp);
                    break;
                case "br":
                    iv.setImageResource(R.drawable.br);
                    break;
                case "bh":
                    iv.setImageResource(R.drawable.bn);
                    break;
                case "bb":
                    iv.setImageResource(R.drawable.bb);
                    break;
                case "bq":
                    iv.setImageResource(R.drawable.bq);
                    break;
                case "bk":
                    iv.setImageResource(R.drawable.bk);
                    break;
                case "bp":
                    iv.setImageResource(R.drawable.bp);
                    break;
            }
            if (selectedposn < 10) {
                images[selectedposn].setTag("0" + selectedposn);
            } else {
                images[selectedposn].setTag(selectedposn);
            }
            images[selectedposn].setImageResource(0);
            clearpre();
            // kingbudalaka function should be after clearpre
           /* for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (images[findno(i, j)].getTag().toString().length() == 4)
                    {
                        if(images[findno(i,j)].getTag().toString().substring(2,3).equals(color[turn+1]))
                        {
                            String picee=images[findno(i,j)].getTag().toString().substring(3);
                            switch (picee) {
                                case "r": {
                                    rook(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                                case "h": {
                                    horse(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                                case "b": {
                                    bishop(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                                case "q": {
                                    queen(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                                case "k": {
                                    king(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                                case "p": {
                                    pawn(i, j, color[turn + 1]);
                                    king_budala_ka();
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            kingendangered=-1;
            */
            turn = turn * (-1);
        }
        if ((tag.length() == 2 && (dra.getColor() == Color.parseColor("#ffffff") || dra.getColor() == Color.parseColor("#bebebe"))) || ((tag.substring(2, 3).equals("b") && turn > 0) || (tag.substring(2, 3).equals("w") && turn < 0))) {
            clearpre();
        }
    }
        catch(Exception r)
        {
            display.setText(r.getLocalizedMessage());
        }
        }

    /*private void king_budala_ka() {
        calculate cal=new calculate();
        if(kingendangered>0)
        {
            cal.findxy(kingendangered);
            if((cal.r+cal.c)%2==0)
            {
                images[kingendangered].setBackgroundColor(Color.parseColor("#ffb2b2"));
            }
            else
            {
                images[kingendangered].setBackgroundColor(Color.parseColor("#ff3232"));
            }
        }
    }*/

    private void rook(int row, int col, String colour) {
        clearpre();
        int nr, sr, ec, wc, i;
        //Find east column
        for (i = col + 1; i < 9; i++) {
            if (images[findno(row, i)].getTag().toString().length() == 4) {
                break;
            }
        }
        ec = i - 1;
        if (ec < 8) {
            if (!images[findno(row, ec + 1)].getTag().toString().substring(2, 3).equals(colour)) {
                ec++;
            }
        }
        //Find west row and column
        for (i = col - 1; i > 0; i--) {
            if (images[findno(row, i)].getTag().toString().length() == 4) {
                break;
            }
        }
        wc = i + 1;
        if (wc > 1) {
            if (!images[findno(row, wc - 1)].getTag().toString().substring(2, 3).equals(colour)) {
                wc--;
            }
        }
        //Finding north row and column
        for (i = row - 1; i > 0; i--) {
            if (images[findno(i, col)].getTag().toString().length() == 4) {
                break;
            }
        }
        nr = i + 1;
        if (nr > 1) {
            if (!images[findno(nr - 1, col)].getTag().toString().substring(2, 3).equals(colour)) {
                nr--;
            }
        }
        //calculating south row and colum
        for (i = row + 1; i < 9; i++) {
            if (images[findno(i, col)].getTag().toString().length() == 4) {
                break;
            }
        }
        sr = i - 1;
        if (sr < 8) {
            if (!images[findno(sr + 1, col)].getTag().toString().substring(2, 3).equals(colour)) {
                sr++;
            }
        }
        //shading the calculated paths
        //x axis first
        for (i = wc; i <= ec; i++) {
            setcolor(row, i);
        }
        //y axis second first
        for (i = nr; i <= sr; i++) {
            setcolor(i, col);
        }
        if ((row + col) % 2 == 0) {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#bebebe"));
        }
        selectedpiece = colour + "r";
        selectedposn = findno(row, col);

    }


    public void clearpre() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if ((i + j) % 2 == 0) {
                    images[findno(i, j)].setBackgroundColor(Color.parseColor("#ffffff"));
                } else {
                    images[findno(i, j)].setBackgroundColor(Color.parseColor("#bebebe"));
                }
            }
        }
        selectedpiece = null;
        selectedposn = 0;
        kingendangered=-1;
    }


    private void horse(int row, int col, String colour) {
            clearpre();
            int f_s, f_n, n_s, n_n, n_e, n_w, f_e, f_w;
            f_s = row + 2;
            f_n = row - 2;
            n_s = row + 1;
            n_n = row - 1;
            n_e = col + 1;
            n_w = col - 1;
            f_e = col + 2;
            f_w = col - 2;
            if (f_s > 8) {
                f_s = 0;
            }
            if (f_n > 8) {
                f_n = 0;
            }
            if (n_s > 8) {
                n_s = 0;
            }
            if (n_n > 8) {
                n_n = 0;
            }
            if (n_e > 8) {
                n_e = 0;
            }
            if (n_w > 8) {
                n_w = 0;
            }
            if (f_e > 8) {
                f_e = 0;
            }
            if (f_w > 8) {
                f_w = 0;
            }
            //display.setText(row+"row\t"+col+"col\n"+f_e+"fe\t"+n_e+"ne\t"+f_w+"fw\t"+n_w+"nw\t"+f_n+"fn\t"+n_n+"nn\t"+f_s+"fs\t"+n_s+"ns\t");
            //check the below "if" code to get idea of what u did earlier
            if (f_s > 0 && n_e > 0) {
                if (images[findno(f_s, n_e)].getTag().toString().length() == 4) {
                    if (!images[findno(f_s, n_e)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(f_s, n_e);
                    }
                } else {
                    setcolor(f_s, n_e);
                }
            }
            if (f_s > 0 && n_w > 0) {
                if (images[findno(f_s, n_w)].getTag().toString().length() == 4) {
                    if (!images[findno(f_s, n_w)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(f_s, n_w);
                    }
                } else {
                    setcolor(f_s, n_w);
                }
            }
            if (f_n > 0 && n_e > 0) {
                if (images[findno(f_n, n_e)].getTag().toString().length() == 4) {
                    if (!images[findno(f_n, n_e)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(f_n, n_e);
                    }
                } else {
                    setcolor(f_n, n_e);
                }
            }
            if (f_n > 0 && n_w > 0) {

                if (images[findno(f_n, n_w)].getTag().toString().length() == 4) {
                    if (!images[findno(f_n, n_w)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(f_n, n_w);
                    }
                } else {
                    setcolor(f_n, n_w);
                }
            }
            if (n_s > 0 && f_e > 0) {
                if (images[findno(n_s, f_e)].getTag().toString().length() == 4) {
                    if (!images[findno(n_s, f_e)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(n_s, f_e);
                    }
                } else {
                    setcolor(n_s, f_e);
                }
            }

            if (n_n > 0 && f_e > 0) {
                if (images[findno(n_n, f_e)].getTag().toString().length() == 4) {
                    if (!images[findno(n_n, f_e)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(n_n, f_e);
                    }
                } else {
                    setcolor(n_n, f_e);
                }
            }

            if (n_s > 0 && f_w > 0) {
                if (images[findno(n_s, f_w)].getTag().toString().length() == 4) {
                    if (!images[findno(n_s, f_w)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(n_s, f_w);
                    }
                } else {
                    setcolor(n_s, f_w);
                }
            }

            if (n_n > 0 && f_w > 0) {
                if (images[findno(n_n, f_w)].getTag().toString().length() == 4) {
                    if (!images[findno(n_n, f_w)].getTag().toString().substring(2, 3).equals(colour)) {
                        setcolor(n_n, f_w);
                    }
                } else {
                    setcolor(n_n, f_w);
                }
            }

            selectedpiece = colour + "h";
            selectedposn = findno(row, col);

    }

    public void setcolor(int row, int col) {
        int enemy=turn*-1;
        if ((row + col) % 2 == 0) {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#a1e067"));
        } else {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#80b352"));
        }
        if(images[findno(row,col)].getTag().toString().length()==4)
        {
            if(images[findno(row,col)].getTag().toString().substring(3).equals("k")&&images[findno(row,col)].getTag().toString().substring(2,3).equals(color[enemy+1]))
            {
                kingendangered=findno(row,col);
            }
        }
    }

    private void bishop(int row, int col, String colour) {
            clearpre();
            int i, j, ne_r, ne_c, nw_r, nw_c, se_r, se_c, sw_r, sw_c;
            //calculating northeast limit
            for (i = row-1, j = col+1; i > 0 && j < 9; i--, j++) {
                if (images[findno(i, j)].getTag().toString().length() == 4) {
                    break;
                }
            }
            if(i<1||j>8)
            {
                i++;
                j--;
            }
            ne_r=i;
            ne_c=j;
            if (images[findno(ne_r,ne_c)].getTag().toString().length()==4) {
                if (images[findno(ne_r, ne_c)].getTag().toString().substring(2, 3).equals(colour)) {
                    ne_r++;
                    ne_c--;
                }
            }
            //calculating northwest limit
            for (i = row-1, j = col-1; i > 0 && j > 0; i--, j--) {
                if (images[findno(i, j)].getTag().toString().length() == 4) {
                    break;
                }
            }
            if(i<1||j<1)
            {
                i++;
                j++;
            }
            nw_r=i;
            nw_c=j;
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                if (images[findno(nw_r, nw_c)].getTag().toString().substring(2, 3).equals(colour)) {
                    nw_r++;
                    nw_c++;
                }
            }
            //implementing southeast limit
            for (i = row+1, j = col+1; i < 9 && j < 9; i++, j++) {
                if (images[findno(i, j)].getTag().toString().length() == 4) {
                    break;
                }
            }
            if(i>8||j>8)
            {
                i--;
                j--;
            }
                se_r=i;
                se_c=j;
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                if (images[findno(se_r, se_c)].getTag().toString().substring(2, 3).equals(colour)) {
                    se_r--;
                    se_c--;
                }
            }
            //Implementing southwest limit
            for (i = row+1, j = col-1; i < 9 && j > 0; i++, j--) {
                if (images[findno(i, j)].getTag().toString().length() == 4) {
                    break;
                }
            }
            if(i>8||j<1)
            {
                i--;
                j++;
            }
                sw_r=i;
                sw_c=j;

            if (images[findno(i, j)].getTag().toString().length() == 4) {
                if (images[findno(sw_r, sw_c)].getTag().toString().substring(2, 3).equals(colour)) {
                    sw_r--;
                    sw_c++;
                }
            }
            //shading northeast to southwest diagonal
            for (i = ne_r, j = ne_c; i <= sw_r && j >= sw_c; i++, j--) {
                setcolor(i, j);
            }
            //shading northwest to southeast
            for (i = nw_r, j = nw_c; i <= se_r && j <= se_c; i++, j++) {
                setcolor(i, j);
            }
            if ((row + col) % 2 == 0) {
                images[findno(row, col)].setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                images[findno(row, col)].setBackgroundColor(Color.parseColor("#bebebe"));
            }
            selectedposn = findno(row, col);
            selectedpiece = colour + "b";

    }

    private void queen(int row, int col, String colour) {
        clearpre();
        int i, j, ne_r, ne_c, nw_r, nw_c, se_r, se_c, sw_r, sw_c;
        //calculating northeast limit
        for (i = row-1, j = col+1; i > 0 && j < 9; i--, j++) {
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                break;
            }
        }
        if(i<1||j>8)
        {
            i++;
            j--;
        }
        ne_r=i;
        ne_c=j;
        if (images[findno(ne_r,ne_c)].getTag().toString().length()==4) {
            if (images[findno(ne_r, ne_c)].getTag().toString().substring(2, 3).equals(colour)) {
                ne_r++;
                ne_c--;
            }
        }
        //calculating northwest limit
        for (i = row-1, j = col-1; i > 0 && j > 0; i--, j--) {
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                break;
            }
        }
        if(i<1||j<1)
        {
            i++;
            j++;
        }
        nw_r=i;
        nw_c=j;
        if (images[findno(i, j)].getTag().toString().length() == 4) {
            if (images[findno(nw_r, nw_c)].getTag().toString().substring(2, 3).equals(colour)) {
                nw_r++;
                nw_c++;
            }
        }
        //implementing southeast limit
        for (i = row+1, j = col+1; i < 9 && j < 9; i++, j++) {
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                break;
            }
        }
        if(i>8||j>8)
        {
            i--;
            j--;
        }
        se_r=i;
        se_c=j;
        if (images[findno(i, j)].getTag().toString().length() == 4) {
            if (images[findno(se_r, se_c)].getTag().toString().substring(2, 3).equals(colour)) {
                se_r--;
                se_c--;
            }
        }
        //Implementing southwest limit
        for (i = row+1, j = col-1; i < 9 && j > 0; i++, j--) {
            if (images[findno(i, j)].getTag().toString().length() == 4) {
                break;
            }
        }
        if(i>8||j<1)
        {
            i--;
            j++;
        }
        sw_r=i;
        sw_c=j;

        if (images[findno(i, j)].getTag().toString().length() == 4) {
            if (images[findno(sw_r, sw_c)].getTag().toString().substring(2, 3).equals(colour)) {
                sw_r--;
                sw_c++;
            }
        }
        //rook part
        int nr, sr, ec, wc;
        //Find east column
        for (i = col + 1; i < 9; i++) {
            if (images[findno(row, i)].getTag().toString().length() == 4) {
                break;
            }
        }
        ec = i - 1;
        if (ec < 8) {
            if (!images[findno(row, ec + 1)].getTag().toString().substring(2, 3).equals(colour)) {
                ec++;
            }
        }
        //Find west row and column
        for (i = col - 1; i > 0; i--) {
            if (images[findno(row, i)].getTag().toString().length() == 4) {
                break;
            }
        }
        wc = i + 1;
        if (wc > 1) {
            if (!images[findno(row, wc - 1)].getTag().toString().substring(2, 3).equals(colour)) {
                wc--;
            }
        }
        //Finding north row and column
        for (i = row - 1; i > 0; i--) {
            if (images[findno(i, col)].getTag().toString().length() == 4) {
                break;
            }
        }
        nr = i + 1;
        if (nr > 1) {
            if (!images[findno(nr - 1, col)].getTag().toString().substring(2, 3).equals(colour)) {
                nr--;
            }
        }
        //calculating south row and colum
        for (i = row + 1; i < 9; i++) {
            if (images[findno(i, col)].getTag().toString().length() == 4) {
                break;
            }
        }
        sr = i - 1;
        if (sr < 8) {
            if (!images[findno(sr + 1, col)].getTag().toString().substring(2, 3).equals(colour)) {
                sr++;
            }
        }
        //shading northeast to southwest diagonal
        for (i = ne_r, j = ne_c; i <= sw_r && j >= sw_c; i++, j--) {
            setcolor(i, j);
        }
        //shading northwest to southeast
        for (i = nw_r, j = nw_c; i <= se_r && j <= se_c; i++, j++) {
            setcolor(i, j);
        }
        //shading the calculated paths
        //x axis first
        for (i = wc; i <= ec; i++) {
            setcolor(row, i);
        }
        //y axis second first
        for (i = nr; i <= sr; i++) {
            setcolor(i, col);
        }
        if ((row + col) % 2 == 0) {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            images[findno(row, col)].setBackgroundColor(Color.parseColor("#bebebe"));
        }
        selectedpiece=colour+"q";
        selectedposn=findno(row,col);
        }

    private void king(int row, int col, String colour) {
        clearpre();
        for(int i=row-1;i<=row+1;i++)
        {
            for(int j=col-1;j<=col+1;j++)
            {
                if(i>0&&i<9&&j>0&&j<9)
                {
                    if(images[findno(i,j)].getTag().toString().length()==4)
                    {
                        if(!images[findno(i,j)].getTag().toString().substring(2,3).equals(colour))
                        {
                            setcolor(i, j);
                        }
                    }
                    else {
                        setcolor(i, j);
                    }
                }
            }
        }
        selectedpiece=colour+"k";
        selectedposn=findno(row,col);
    }

    private void pawn(int row, int col, String colour) {
        clearpre();
        int r,c1,c2,c3;
        if(colour.equals("b")) {
            r = row + 1;
        }
        else {
            r = row - 1;
        }
            c1=col;
            c2=col+1;
            c3=col-1;
            if(r>0&&r<9&&c1>0&&c1<9)
            {
                if(images[findno(r,c1)].getTag().toString().length()==2)
                {
                    setcolor(r,c1);
                }
            }
            if(r>0&&r<9&&c2<9&&c2>0)
            {
                if(images[findno(r,c2)].getTag().toString().length()==4)
                {
                    if(!images[findno(r,c2)].getTag().toString().substring(2,3).equals(colour))
                    {
                        setcolor(r,c2);
                    }

                }
            }
            if(r>0&&r<9&&c3>0&&c3<9)
            {
                if(images[findno(r,c3)].getTag().toString().length()==4)
                {
                    if(!images[findno(r,c3)].getTag().toString().substring(2,3).equals(colour))
                    {
                        setcolor(r,c3);
                    }

                }
            }
            if(colour.equals("b"))
            {
                if(row==2)
                {
                    if(images[findno(4,col)].getTag().toString().length()==2&&images[findno(3,col)].getTag().toString().length()!=4)
                    {
                        setcolor(4,col);
                    }
                }
            }
        else
            {
                if(row==7)
                {
                    if(images[findno(5,col)].getTag().toString().length()==2&&images[findno(6,col)].getTag().toString().length()!=4)
                    {
                        setcolor(5,col);
                    }
                }
            }

            selectedpiece=colour+"p";
        selectedposn=findno(row,col);
        }

    public int findno(int r, int c) {
        int no;
        no = (r - 1) * 8;
        no += c;
        return no;
    }

}
