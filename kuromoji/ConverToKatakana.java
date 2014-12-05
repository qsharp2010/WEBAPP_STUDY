  public class ConverToKatakana {
    // Converts the kanji and hiragana to katakana, the characters can be maintained.
    public String toJapaneseKatakana(List<String> temp) throws IOException {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < temp.size(); i++) {
            Reader reader = new StringReader(temp.get(i));
            TokenStream stream = new JapaneseTokenizer(reader, null, false,
                    JapaneseTokenizer.Mode.NORMAL);
            ReadingAttribute term = stream.getAttribute(ReadingAttribute.class);
            TermToBytesRefAttribute termAtt = (TermToBytesRefAttribute) stream
                    .getAttribute(TermToBytesRefAttribute.class);
            stream.reset();
            while (stream.incrementToken()) {
                if (!this.isEmptyString(term.getReading())) {
                    str.append(term.getReading());
                } else {
                    str.append(termAtt);
                }
            }
            stream.close();
            str.append(ElementsTypeDefinition.halfSpace);
        }
        return str.toString().trim();
    }
  }
